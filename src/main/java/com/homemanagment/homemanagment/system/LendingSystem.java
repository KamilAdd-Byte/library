package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

@Service
public interface LendingSystem {
    void lendingBook (UserLending userLending, int id,Book book);
    void recoveredBook (UserLending userLending, Book book);
}
