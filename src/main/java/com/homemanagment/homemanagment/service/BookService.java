package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public interface BookService {
    List<Book> allBooks();

    void saveBook(Book book);

    void removeBook(long id, Book book);

    Optional<Book> findBookById(long id);

}
