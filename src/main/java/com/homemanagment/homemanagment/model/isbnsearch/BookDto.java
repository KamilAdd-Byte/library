package com.homemanagment.homemanagment.model.isbnsearch;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDto {
    private String title;
    private String author;
    private String isbn;
}
