package com.homemanagment.homemanagment.restController;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.impl.UserServiceImpl;
import com.homemanagment.homemanagment.system.LibraryHomeSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LibraryRestController {

    private final UserServiceImpl userService;
    private final UserDao userRepository;
    private final BookDao bookRepository;
    private final LibraryHomeSystem lhs;

    @Autowired
    public LibraryRestController(final UserServiceImpl userService, final UserDao userRepository,
                                 BookDao bookRepository, final LibraryHomeSystem hs) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.lhs = hs;
    }

    @PostMapping("/lending")
    ResponseEntity<?> lendingBook(@ModelAttribute("book")Book book,
                                  @ModelAttribute("user") UserLending userLending, Model model){
        model.addAttribute("listAllUser",userRepository.findAll());
        userService.lendingBook(book,userLending);
        return ResponseEntity.noContent().build();
    }
}
