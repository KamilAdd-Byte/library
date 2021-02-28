package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private final BookDao repository;

    @Autowired
    private final BookServiceImpl service;

    public BookController(final BookDao repository, final BookServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/index")
    public String showFormListAllBooks(Model model){
        model.addAttribute("listAllBook",repository.findAll());
        model.addAttribute("message","Usunięto z bazy");
        return "index";
    }
    @GetMapping("/")
    public String showAllBooks(Model model){
       return findPaginated(1,model);
    }
//    @GetMapping("/")
//    public String showOnBooks(Model model){
//        model.addAttribute("listAllBook",repository.findAll());
//        return "index";
//    }


    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model){
        Book book = new Book();
        model.addAttribute("book",book);
        return "new_book";
    }
    @PostMapping(value = "/save_book")
    public String saveNewBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult){
       if (bindingResult.hasErrors()){
           List<ObjectError> objectErrorList = bindingResult.getAllErrors();
           objectErrorList.forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
           return "new_book";
       }else
        service.saveBook(book);
        return "new_book_info";
    }

    @GetMapping(value = "/new_book_info")
    public String showNewBook(Model model){
        model.addAttribute("book",new Book());
        return "index";
    }
    @GetMapping("/remove/{id}")
    public String removeBookById (@PathVariable("id") long id,Book book,Model model){
        service.removeBookById(id,book);
        model.addAttribute("message","Pomyślnie usunięto książkę!");
        return "redirect:/index";
    }
    @GetMapping("/update/{id}")
    public String updateBookById (@PathVariable("id") long id, Model model){
        service.findBookByID(id);
        model.addAttribute("message","Pomyślnie edytowano książkę!");
        model.addAttribute("book",service.findBookByID(id));
        return "update_book";
    }
    @RequestMapping("/search")
    public ModelAndView searchBookByTitle (@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("search");
        List<Book> result = service.search(keyword);
        mav.addObject("result",result);

        return mav;
    }

    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber,Model model,
             @RequestParam("sortField") String sortfield,
             @RequestParam("sortDir") String sortDir){
        //local variable how book on one page
        int pageSize = 5;
        //create page reference and get bookList to page getContent method
        Page<Book> page = service.findPaginated(pageNumber,pageSize,sortfield,sortDir);
        List<Book> bookList = page.getContent();
        //paginated attribute
        model.addAttribute("currentPage",pageNumber);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listAllBooks",bookList);
        //sorting attribute
        model.addAttribute("sortField",sortfield);
        model.addAttribute("sortDirection",sortDir);
        model.addAttribute("reverseSortDirection",sortDir.equals("asc") ? "desc" : "asc");
        return "index";
    }
}
