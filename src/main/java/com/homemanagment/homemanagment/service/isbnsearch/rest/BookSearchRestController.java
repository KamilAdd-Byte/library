package com.homemanagment.homemanagment.service.isbnsearch.rest;

import com.homemanagment.homemanagment.model.isbnsearch.BookDto;
import com.homemanagment.homemanagment.service.isbnsearch.BookSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookSearchRestController {

    private final BookSearchService bookSearchService;

    @GetMapping("/search/{isbn}")
    public BookDto getBookForIsbn (@PathVariable("isbn") String isbn){
        return bookSearchService.getBook(isbn);
    }
}
