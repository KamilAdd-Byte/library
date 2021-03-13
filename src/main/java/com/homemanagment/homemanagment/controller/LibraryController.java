package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import com.homemanagment.homemanagment.service.UserServiceImpl;
import com.homemanagment.homemanagment.system.LibraryHomeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final BookServiceImpl service;

    private final UserDao userRepository;

    @Autowired
    private final LibraryHomeSystem hs;

    public LibraryController(final UserServiceImpl userService, final BookServiceImpl service, UserDao userRepository, LibraryHomeSystem hs) {
        this.userService = userService;
        this.service = service;
        this.userRepository = userRepository;
        this.hs = hs;
    }

    @GetMapping("/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,
                                  @ModelAttribute("user") UserLending userLending, Model model){
        Book book = service.findBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("list",userRepository.findAll());
        model.addAttribute("user", userLending);
       return "/lending";
    }

}
