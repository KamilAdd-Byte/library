package com.homemanagment.homemanagment.restController;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.system.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class LibraryRestController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/lending")
    ResponseEntity<?> lendingBook(@ModelAttribute("book")Book book,
                                  @ModelAttribute("user")UserLending user){
        libraryService.lendingBook(user,book);
        return ResponseEntity.noContent().build();
    }

}
