package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public List<UserLending> allUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(UserLending userLending) {
        this.userRepository.save(userLending);
    }

    @Override
    @Transactional
    public void removeUser(int id, UserLending userLending) {
        this.userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UserLending findUserByID(int id) {
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    @Transactional
    public void addBookToUserCollection(UserLending userLending, Book book) {
        UserLending borrower = userRepository.findById(userLending.getId()).orElseThrow(IllegalArgumentException::new);
        Book bookToBorrow = bookRepository.findById(book.getId()).orElseThrow(IllegalArgumentException::new);

    }
}
