package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserLending> allUsers();
    void addUser(UserLending userLending);
    void removeUser(UserLending userLending);
    void lendingBook(Book book,UserLending userLending);
    boolean recoveredBook (Book book);

}
