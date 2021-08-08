package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserService userService;


    @Autowired
    SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public Book saveBook(Book book) {
       book.setBookStatus(BookStatus.AVAILABLE);
       return this.bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book findBookByID(int id) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return book;
    }

    @Override
    @Transactional
    public void removeBookById(int id, Book book) {
        this.bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBookById(int id, Book book) {
        this.bookRepository.findById(id);
        //TODO Update method!!!! Override and create new book instead update
    }

    @Override
    @Transactional
    public Book lendBook(int id, UserLending borrower) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBookStatus(BookStatus.BORROWED);
        setBorrower(borrower, book);
        return bookRepository.save(book);
    }

    private void setBorrower(UserLending borrower, Book book) {
        book.setBorrower(borrower);
        addBookToUserList(borrower, book);
    }

    private void addBookToUserList(UserLending borrower, Book book) {
        Set<Book> books = borrower.getBooks();
        if (books==null){
            books = new HashSet<>();
        }
        books.add(book);
    }

    @Override
    @Transactional
    public void giveBackBook(int id, UserLending borrower) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBookStatus(BookStatus.AVAILABLE);
        book.setBorrower(null);
        bookRepository.save(book);
    }


    @Override
    public Page<Book> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
             Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber -1,pageSize,sort);
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public List<Book> search(String keyword) {
        try {
            return bookRepository.searchBookByTitle(keyword);
        } catch (NullPointerException e) {
            e.getStackTrace();
        }
        return null;
    }
}
