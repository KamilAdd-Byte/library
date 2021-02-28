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
    public String saveNewBook(@ModelAttribute("book") @Valid Book book,  Model model, BindingResult bindingResult){
       if (bindingResult.hasErrors()){
           List<ObjectError> objectErrorList = bindingResult.getAllErrors();
           objectErrorList.forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
           return "new_book";
       }else
        service.saveBook(book);
        model.addAttribute("message","Dodano ksiązkę do bazy");
        return "redirect:/index";
    }
}
