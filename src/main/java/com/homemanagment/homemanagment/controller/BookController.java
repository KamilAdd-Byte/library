package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
        model.addAttribute("listAllBook",repository.findAll());
        return "index";
    }
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
    public String updateBookById (@PathVariable("id") long id,Book book,Model model){
        service.updateBookById(id, book);
        model.addAttribute("message","Pomyślnie edytowano książkę!");
        model.addAttribute("book",service.findBook(id, book));
        return "update_book";
    }

}
