package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
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

    @Autowired
    public LibraryController(final BookService bookService, final UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping("/lending")
    public String lending (Model model){
        model.addAttribute("allUsers",userService.allUsers());
      return "lending";
    }
    @GetMapping("/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,
                                  @ModelAttribute UserLending userLending, Model model) {
       Book book = bookService.findBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("allUsers",userService.allUsers());
        model.addAttribute("borrower",userLending);
        return "lending";
    }
    @GetMapping("/lending/{id}/userLending")
    public String lendBookById(@PathVariable("id") int id,
                                  @ModelAttribute UserLending userLending, Model model) {
        Book book = bookService.findBookByID(id);
        bookService.lendBook(id,userLending);

        model.addAttribute("allUsers",userService.allUsers());
        model.addAttribute("book", book);
        model.addAttribute("borrower", userLending);
        model.addAttribute("listUserCollectionBooks",userLending.getBooks());
        return "management";
    }

    @GetMapping("/giveBackBook/{id}")
    public String giveBackBookById(@PathVariable("id") int id,
                                  @ModelAttribute UserLending userLending, Model model) {
        Book book = bookService.findBookByID(id);
        bookService.giveBackBook(id,userLending);
        model.addAttribute("book",book);
        model.addAttribute("allUsers",userService.allUsers());
        model.addAttribute("borrower",userLending);
        return "management";
    }
}
