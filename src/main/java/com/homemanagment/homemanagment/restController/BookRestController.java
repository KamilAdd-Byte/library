package com.homemanagment.homemanagment.restController;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequestMapping("/book")
public class BookRestController {
    @Autowired
    private final BookDao repository;

    @Autowired
    private final BookServiceImpl service;

    public BookRestController(final BookDao repository, final BookServiceImpl service) {
        this.repository = repository;
        this.service = service;
    }
    @PostMapping("/add")
    public String addBook(@RequestBody Book book){
        service.saveBook(book);
         return "Done!";
        }
}
