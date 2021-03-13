package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.repositories.UserDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


    @Override
    public List<UserLending> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(UserLending userLending) {
        this.userRepository.save(userLending);
    }
    @Override
    public void removeUser(UserLending userLending) {
        this.userRepository.delete(userLending);
    }

    @Override
    public void lendingBook(Book book) {
       booksLendingList.add(book);
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

    @Override
    public void addBookToUserCollection(Book bookLending) {
        booksLendingList.add(bookLending);
    }
}
