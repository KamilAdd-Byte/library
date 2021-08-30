package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final BookRepository repository;

    private final BookService service;

    @Autowired
    public IndexController(final BookRepository repository, final BookService service) {
        this.repository = repository;
        this.service = service;
    }

    @GetMapping("/")
    public String viewInfo(Model model){
        model.addAttribute("borrowedBooks",service.getBorrowedBooks());
        return "index";
    }
    @GetMapping("/index")
    public String viewBorrowedBooks(Model model){
        model.addAttribute("borrowedBooks",service.getBorrowedBooks());
        return "index";
    }
}
