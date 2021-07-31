package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    private final BookService bookService;
    private final UserService userService;

    public LibraryController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,
                                  @ModelAttribute("user") UserLending userLending, Model model) {
       Book book = bookService.findBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("allUsers",userService.allUsers());
        model.addAttribute("borrower",userLending);
       book.setBookStatus(BookStatus.BORROWED);
        //TODO
        return "/lending";
    }

}
