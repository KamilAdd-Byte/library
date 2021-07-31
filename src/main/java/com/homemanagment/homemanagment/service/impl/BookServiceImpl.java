package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.service.BookService;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookRepository;

    @Autowired
    private UserServiceImpl userService;


    @Autowired
    SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    @Transactional
    public Book findBookByID(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.get();
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
    public Book lendBook(int id, UserLending borrower) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBookStatus(BookStatus.BORROWED);
        book.setBorrower(borrower);
        return bookRepository.save(book);
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
