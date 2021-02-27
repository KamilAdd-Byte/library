package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.repositories.BookDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao repository;

    @Autowired
    SessionFactory sessionFactory;

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
    public Book findBookById(long id, Book book) {
        return this.repository.getOne(id);
    }

    @Override
    @Transactional
    public void removeBookById(long id, Book book) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBookById(long id, Book book) {
        Book update = repository.getOne(id);
        update.setAuthor(book.getAuthor());
        update.setTitle(book.getTitle());
        update.setDescription(book.getDescription());
        update.setIsbn(book.getIsbn());
        update.setLocalization(book.getLocalization());
        this.repository.save(update);
    }
}
