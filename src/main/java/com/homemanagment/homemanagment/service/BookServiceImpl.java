package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookDao repository;

    @Override
    @Transactional
    public List<Book> allBooks() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        this.repository.save(book);
    }

    @Override
    @Transactional
    public void removeBook(long id, Book book) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Book> findBookById(long id) {
        return repository.findById(id);
    }
}
