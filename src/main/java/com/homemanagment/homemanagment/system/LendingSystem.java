package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.LendingBooks;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LendingSystem {
    List<LendingBooks> allLendingBooks();
    void addLendingOperation(LendingBooks lendingBooks);
    void lendingBook (Book book);
    boolean recoveredBook (Book book);
    void removeAllBooks();
}
