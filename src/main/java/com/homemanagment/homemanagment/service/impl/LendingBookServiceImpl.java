package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.LendingBooks;
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
    public List<LendingBooks> allLendingBooksList() {
        return lendingRepository.findAll();
    }
    @Override
    @Transactional
    public void addLendingOperation(UserLending userLending,Book book){
        int userLendingId = userLending.getId();
        int bookId = book.getId();
        userRepository.findById(userLendingId);
        bookRepository.findById(bookId);

    }
    @Override
    @Transactional
    public void createNewLending(Book book, UserLending userLending) {

    }
}
