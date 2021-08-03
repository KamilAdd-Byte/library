package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import java.util.List;

public interface UserService {

    List<UserLending> allUsers();

    void addUser(UserLending userLending);

    void removeUser(int id,UserLending userLending);

    UserLending findBookByID(int id);

    void addBookToUserList(UserLending userLending,Book book);
}
