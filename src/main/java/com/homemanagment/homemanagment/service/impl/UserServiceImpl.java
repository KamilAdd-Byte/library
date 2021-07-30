package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.type.StatusLending;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.LendingBookService;
import com.homemanagment.homemanagment.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Getter
public class UserServiceImpl implements UserService {

    private final List<Book> booksHistoryList = new ArrayList<>();
    private final List<Book> booksLendingList = new ArrayList<>();

    @Autowired
    private UserDao userRepository;

    @Autowired
    private BookDao bookRepository;

    @Autowired
    private LendingBookService lendingBookService;

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
    @Transactional
    public void lendingBook(Book book,UserLending userLending) {
       booksLendingList.add(book);

       book.setUserLending(userLending);
       book.setStatusLending(StatusLending.LENDING);
    }

    @Override
    public boolean recoveredBook(Book book) {
        boolean result = false;
        if (booksLendingList.contains(book)){
            booksLendingList.remove(book);
            result = true;
            booksHistoryList.add(book);
        }
        return result;
    }
}
