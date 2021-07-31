package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.data.domain.Page;
import java.util.List;


public interface BookService {
    List<Book> allBooks();

    void saveBook(Book book);

    Book findBookByID(int id);

    void removeBookById(int id, Book book);

    void updateBookById(int id, Book book);


    Page<Book> findPaginated(int pageNumber,int pageSize,String sortField, String sortDirection);

    List<Book> search(String keyword);
}
