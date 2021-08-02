package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<Book> booksHistoryList;
    private List<Book> booksLendingList;

    @Autowired
    private UserDao userRepository;

    @Autowired
    private BookService bookService;

    public UserServiceImpl(List<Book> booksHistoryList, List<Book> booksLendingList) {
        this.booksHistoryList = booksHistoryList;
        this.booksLendingList = booksLendingList;
    }

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
    public void removeUser(int id,UserLending userLending) {
        this.userRepository.deleteById(id);
    }
    @Override
    @Transactional
    public List<Book> userBooks (UserLending userLending){
        this.booksLendingList = new ArrayList<>();
        //TODO
        return booksLendingList;
}

}
