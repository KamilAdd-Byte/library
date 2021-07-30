package com.homemanagment.homemanagment.service.isbnsearch;

import com.homemanagment.homemanagment.model.isbnsearch.BookDto;

public interface BookSearchService {
    BookDto getBook (String isbn);
}
