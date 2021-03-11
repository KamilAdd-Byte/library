package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface LendingSystem {
    void addBookToLendingList(Book book);
    boolean checkBookIsLending(Book book);
    void lendingBook (UserLending userLending, Book book);
    void recoveredBook (UserLending userLending, Book book);
    void removeAllBooks();
}
