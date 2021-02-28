package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface BookService {
    List<Book> allBooks();

    void saveBook(Book book);

    Book findBookByID(long id);

    void removeBookById(long id, Book book);

    Book updateBookById(long id);

    Page<Book> findPaginated(int pageNumber,int pageSize);
}
