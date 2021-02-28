package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface BookService {
    List<Book> allBooks();

    void saveBook(Book book);

    Book findBookByID(int id);

    void removeBookById(int id, Book book);

    Book updateBookById(int id);

    Page<Book> findPaginated(int pageNumber,int pageSize,String sortField, String sortDirection);
}
