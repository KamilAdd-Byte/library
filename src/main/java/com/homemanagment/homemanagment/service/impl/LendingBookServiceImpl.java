package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.repositories.LendingBooksDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.LendingBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LendingBookServiceImpl implements LendingBookService {

    @Autowired
    private LendingBooksDao lendingRepository;

   @Autowired
   private UserServiceImpl userService;

   @Autowired
   private UserDao userRepository;

    @Autowired
    private BookDao bookRepository;

    @Override
    @Transactional
    public List<Book> allLendingBooksList() {
        return lendingRepository.findAll();
    }

    @Override
    @Transactional
    public void recoverBook(UserLending userLending, Book book) {

    }


    @Override
    @Transactional
    public void createNewLending(Book book, UserLending userLending) {

    }
}
