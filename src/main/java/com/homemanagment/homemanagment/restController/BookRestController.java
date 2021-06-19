package com.homemanagment.homemanagment.restController;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookDao repository;
    private final BookServiceImpl service;

    @Autowired
    public BookRestController(final BookDao repository, final BookServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }
    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        service.saveBook(book);
         return "Done!";
        }

//    @PostMapping("/lending")
//    public void lendingBook (@RequestBody UserLending userLending, @RequestBody Book book){
//        service.lendingBook(book, userLending);
//    }
}
