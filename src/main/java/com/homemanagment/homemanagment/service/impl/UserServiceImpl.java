package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.Borrower;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public List<Borrower> allUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(Borrower borrower) {
        this.userRepository.save(borrower);
    }

    @Override
    @Transactional
    public void removeUser(int id, Borrower borrower) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Borrower findUserByID(int id) {
        Borrower borrower = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return borrower;
    }

    @Override
    @Transactional
    public void addBookToUserCollection(Borrower userLending, Book book) {
        Borrower borrower = userRepository.findById(userLending.getId()).orElseThrow(IllegalArgumentException::new);
        Book bookToBorrow = bookRepository.findById(book.getId()).orElseThrow(IllegalArgumentException::new);
    }
}
