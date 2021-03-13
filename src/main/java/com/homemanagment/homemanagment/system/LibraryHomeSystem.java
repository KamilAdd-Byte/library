package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class LibraryHomeSystem implements LendingSystem{

    private final Map<Integer, UserLending> users = new HashMap<>();

    UserServiceImpl userService;

    private BookDao bookRepository;

    @Autowired
    public LibraryHomeSystem(UserServiceImpl userService, BookDao bookRepository) {
        this.userService = userService;
        this.bookRepository = bookRepository;
    }

    @Override
    public void addBookToHistoryList(Book book) {

    }

    @Override
    public void addBookToLendingList(Book book) {

    }

    @Override
    public boolean checkBookIsLending(Book book) {
        return false;
    }

    @Override
    public void lendingBook(UserLending userLending, Book book){

            userService.lendingBook(book);
    }

    @Override
    public boolean recoveredBook(Book book) {
        return false;
    }

    @Override
    public void removeAllBooks() {

    }


}
