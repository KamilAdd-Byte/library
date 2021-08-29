package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BookService {
    List<Book> allBooks();

    Book saveBook(Book book);

    Book findBookByID(int id);

    void removeBookById(int id, Book book);

    void updateBookById(int id, Book book);

    Book lendBook (int id, UserLending borrower);

    Book giveBackBook(int id, UserLending borrower);

    Page<Book> findPaginated(int pageNumber,int pageSize,String sortField, String sortDirection);

    List<Book> search(String keyword);
}
