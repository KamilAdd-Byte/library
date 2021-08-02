package com.homemanagment.homemanagment.restController;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/lending")
public class BookRestController {

    private final BookDao bookRepository;
    private final BookService bookService;

    public BookRestController(final BookDao bookRepository, final BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }



    @PostMapping("/lending/{id}/borrower")
    public ResponseEntity<Book> lendingBook (@PathVariable("id") int id, @RequestBody UserLending userLending){
        Book borrowed = bookService.findBookByID(id);
        borrowed = bookService.lendBook(id, userLending);
        return ResponseEntity.ok().body(borrowed);
    }
}
