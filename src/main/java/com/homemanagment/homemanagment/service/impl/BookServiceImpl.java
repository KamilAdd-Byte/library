package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.repositories.BookRepository;
import com.homemanagment.homemanagment.repositories.UserRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final UserService userService;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    SessionFactory sessionFactory;

    public BookServiceImpl(final BookRepository bookRepository,final UserService userService, final UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }


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
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
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
    }

    @Override
    @Transactional
    public Book lendBook(int id, UserLending borrower) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBorrower(borrower);
        book.setBookStatus(BookStatus.BORROWED);
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getBorrowedBooks() {
        List<Book> bookBorrowedList = bookRepository.findAll().stream()
                .filter(book -> book.getBookStatus() == BookStatus.BORROWED)
                .collect(Collectors.toList());
        if (bookBorrowedList==null){
            return new ArrayList<>();
        }
        return bookBorrowedList;
    }


    @Override
    @Transactional
    public Book giveBackBook(int id, UserLending borrower) {
        Book book = bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        book.setBorrower(book.getBorrower());
        book.setBookStatus(BookStatus.AVAILABLE);
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
        Stream<Book> bookStream = bookRepository.searchBookByTitle(keyword).stream();
        return bookStream
                .filter(b -> b.getTitle().contains(keyword))
                .collect(Collectors.toList());
    }
}
