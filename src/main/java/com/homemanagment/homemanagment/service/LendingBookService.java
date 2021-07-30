package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.LendingBooks;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LendingBookService {
    List<LendingBooks> allLendingBooksList();
    void addLendingOperation(UserLending userLending,Book book);
    void createNewLending(Book book, UserLending userLending);
}
