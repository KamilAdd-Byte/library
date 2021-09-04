package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.Borrower;
import org.springframework.data.domain.Page;
import java.util.List;

public interface BookService {
    List<Book> allBooks();

    Book saveBook(Book book);

    Book findBookByID(int id);

    void removeBookById(int id, Book book);

    void updateBookById(int id, Book book);

    Book lendBook (int id, Borrower borrower);

    List<Book> getBorrowedBooks();

    Book giveBackBook(int id, Borrower borrower);

    Page<Book> findPaginated(int pageNumber,int pageSize,String sortField, String sortDirection);

    List<Book> search(String keyword);
}
