package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

@Service
public interface LendingSystem {
    void addBookToHistoryList(Book book);
    void addBookToLendingList(Book book);
    boolean checkBookIsLending(Book book);
    void lendingBook (UserLending userLending, Book book);
    boolean recoveredBook (Book book);
    void removeAllBooks();
}
