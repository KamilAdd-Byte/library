package com.homemanagment.homemanagment.service.isbnsearch.impl;

import com.homemanagment.homemanagment.model.isbnsearch.BookDto;
import com.homemanagment.homemanagment.service.isbnsearch.BookSearchService;
import com.homemanagment.homemanagment.service.isbnsearch.client.BookClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSearchServiceImpl implements BookSearchService {

    private final BookClient bookClient;

    @Override
    public BookDto getBook(String isbn) {
        return bookClient.getBookForIsbn(isbn);
    }
}
