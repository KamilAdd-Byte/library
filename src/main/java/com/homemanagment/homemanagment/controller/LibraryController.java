package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibraryController {

    private final BookService bookService;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public LibraryController(final BookService bookService, final BookRepository bookRepository,
                             final UserService userService,
                             final UserRepository userRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/lending")
    public String lending (Model model){
        model.addAttribute("allBorrower",userService.allUsers());
      return "lending";
    }
    @GetMapping("/lending/{id}")
    public String lendingBookById(@PathVariable("id") int id,Model model) {
        Book book = bookService.findBookByID(id);
        model.addAttribute("book",book);
        model.addAttribute("allBorrower",userService.allUsers());
        return "lending";
    }
    @GetMapping("/lending/{id}/lending")
    public String lendBookByIdByBorrower(@PathVariable("id") int userId,@ModelAttribute Book book, Model model) {
        Book bookToBorrow = bookService.findBookByID(book.getId());
        UserLending userByID = userService.findUserByID(userId);
        bookService.lendBook(bookToBorrow.getId(), userByID);
//        user.addBookToUserCollection(book);
        model.addAttribute("book", book);
        model.addAttribute("borrower", userByID);
        model.addAttribute("message", "Book successfully borrowed!"+ userByID.toString()+ " " + book.toString() +" Back to management");

        return "lending";
    }

    @GetMapping("/giveBackBook/{id}")
    public String giveBackBookById(@PathVariable("id") int id,
                                  @ModelAttribute UserLending userLending, Model model) {
        Book book = bookService.findBookByID(id);
        UserLending user = userService.findUserByID(userLending.getId());
        bookService.giveBackBook(id,user);
        model.addAttribute("message", "Book successfully give back of your collection! Back to management");
        model.addAttribute("book",book);
        model.addAttribute("allUsers",userService.allUsers());
        model.addAttribute("borrower",userLending);
        return "giveback_book";
    }
}
