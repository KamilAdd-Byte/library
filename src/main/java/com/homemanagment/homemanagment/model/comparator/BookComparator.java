package com.homemanagment.homemanagment.model.comparator;

import com.homemanagment.homemanagment.model.Book;

import java.util.Comparator;

public class BookComparator implements Comparator<Book> {
    @Override
    public int compare(Book b1, Book b2) {
        return b1.getTitle().compareTo(b2.getTitle());
    }
}
