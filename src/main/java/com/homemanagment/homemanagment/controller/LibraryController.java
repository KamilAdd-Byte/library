package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.LendingBooksDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class LibraryController {

    private final LendingBooksDao lendingBooksDao;
    private final BookServiceImpl service;
    private final UserDao userRepository;

    @Autowired
    public LibraryController(LendingBooksDao lendingBooksDao, final BookServiceImpl service, UserDao userRepository) {
        this.lendingBooksDao = lendingBooksDao;
        this.service = service;
        this.userRepository = userRepository;

    }

    @GetMapping("/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,
                                  @ModelAttribute("user") UserLending userLending, Model model) {
        Book book = service.findBookByID(id);
        model.addAttribute("book", book);
        model.addAttribute("list", userRepository.findAll());
        model.addAttribute("lendingListAllBooks", lendingBooksDao.findAll());
        model.addAttribute("user", userLending);
        return "/lending";
    }

}
