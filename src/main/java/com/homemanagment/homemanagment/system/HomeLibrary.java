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

    private final List<Book>lendingBookList = new ArrayList<>();

    public HomeLibrary() {
    }

    public List<Book> getLendingBookList(){
        return lendingBookList;
    }

    @Override
    public void lendingBook(UserLending userLending, int id, Book book) {
        if (!book.isLending()){
            book.setLending(true);
            lendingBookList.add(book);
            book.setUserLending(userLending);
        }else
            System.out.println("Book actual is lending");

    }

    @Override
    public void recoveredBook(UserLending userLending, Book book) {
        if (book.isLending()){
            book.setLending(false);
            this.lendingBookList.remove(book);
            book.setUserLending(null);
            System.out.println("Book recovered to set privat home library!");
        }else
            System.out.println("The book could not be recovered");
    }
}
