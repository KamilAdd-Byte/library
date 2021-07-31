package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import java.util.List;

public interface UserService {
    List<UserLending> allUsers();
    void addUser(UserLending userLending);
    void removeUser(UserLending userLending);
    UserLending lendingBook(Book book);
    UserLending recoveredBook (Book book);
}
