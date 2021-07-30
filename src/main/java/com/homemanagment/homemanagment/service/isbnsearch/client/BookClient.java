package com.homemanagment.homemanagment.service.isbnsearch.client;

import com.homemanagment.homemanagment.model.isbnsearch.BookDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BookClient {

    private static final  String BOOK_SEARCH_API = "https://isbnsearch.org/isbn/";
    private final RestTemplate restTemplate = new RestTemplate();

    public BookDto getBookForIsbn (String isbn) {
        BookDto bookDto = restTemplate.getForObject(BOOK_SEARCH_API + isbn,BookDto.class,isbn);
        return BookDto.builder()
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .isbn(bookDto.getIsbn())
                .build();
    }

}
