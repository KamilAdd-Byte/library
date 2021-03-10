package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeLibrary implements LendingSystem{

    @Autowired
    private BookDao repository;

    @Override
    public List<Book> allLendingCollection() {
        return new ArrayList<>();
    }

    @Override
    public List<UserLending> allLendingUser() {
        return null;
    }

    @Override
    public void lending(UserLending userLending, Book book) {
        if (!book.isLending()){
            book.setLending(true);
            userLending.lendingBook(book);
            allLendingCollection().add(book);
        }else
            System.out.println("Książka jest wypożyczona");
    }

    @Override
    public void turnBack(UserLending userLending, Book book) {

    }

}
