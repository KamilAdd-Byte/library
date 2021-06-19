package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.LendingBooks;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.LendingBooksDao;
import com.homemanagment.homemanagment.service.BookServiceImpl;
import com.homemanagment.homemanagment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryHomeSystem implements LendingSystem{

    private final Map<Integer, UserLending> users = new HashMap<>();

    private final LendingBooksDao lendingBooksRepository;
    private final BookServiceImpl bookService;
    private final UserServiceImpl userService;

    @Autowired
    public LibraryHomeSystem(LendingBooksDao lendingBooksRepository, BookServiceImpl bookService, UserServiceImpl userService) {
        this.lendingBooksRepository = lendingBooksRepository;
        this.bookService = bookService;
        this.userService = userService;
    }

    @Override
    public List<LendingBooks> allLendingBooks() {
        return lendingBooksRepository.findAll();
    }

    @Override
    public void addLendingOperation(LendingBooks lendingBooks) {
        this.lendingBooksRepository.save(lendingBooks);
    }


    @Override
    public void lendingBook(Book book) {

    }

    @Override
    public boolean recoveredBook(Book book) {
        return false;
    }

    @Override
    public void removeAllBooks() {

    }
}
