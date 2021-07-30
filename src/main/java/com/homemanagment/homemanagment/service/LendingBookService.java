package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LendingBookService {
    List<Book> allLendingBooksList();
    void recoverBook(UserLending userLending,Book book);
    void createNewLending(Book book, UserLending userLending);
}
