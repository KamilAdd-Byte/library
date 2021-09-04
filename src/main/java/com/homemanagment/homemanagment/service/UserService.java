package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.Borrower;
import java.util.List;

public interface UserService {

    List<Borrower> allUsers();

    void addUser(Borrower borrower);

    void removeUser(int id, Borrower borrower);

    Borrower findUserByID(int id);

    void addBookToUserCollection(Borrower borrower, Book book);
}
