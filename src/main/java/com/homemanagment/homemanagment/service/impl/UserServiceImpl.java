package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.StatusLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final List<Book> booksHistoryList = new ArrayList<>();
    private final List<Book> booksLendingList = new ArrayList<>();

    @Autowired
    private UserDao userRepository;

    @Autowired
    private BookDao bookRepository;

    @Override
    public List<UserLending> allUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(UserLending userLending) {
        this.userRepository.save(userLending);
    }

    @Override
    @Transactional
    public void removeUser(UserLending userLending) {
        this.userRepository.delete(userLending);
    }

    @Override
    public UserLending lendingBook(Book book) {
        UserLending userLending = new UserLending();
        addUser(userLending);
        book.setStatusLending(StatusLending.LENDING);
        return userLending;
    }

    @Override
    public UserLending recoveredBook(Book book) {
        return null;
    }


}
