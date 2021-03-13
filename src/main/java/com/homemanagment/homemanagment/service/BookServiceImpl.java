package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.BookDao;
import com.homemanagment.homemanagment.system.LibraryHomeSystem;
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
    private BookDao repository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    LibraryHomeSystem lsh;

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
    public Book findBookByID(int id) {
        Optional<Book> book = repository.findById(id);
        return book.get();
    }


    @Override
    @Transactional
    public void removeBookById(int id, Book book) {
        this.repository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBookById(int id, Book book) {
        this.repository.findById(id);
        //TODO Update method!!!! Override and create new book instead update
    }

    // Metoda w servisie łącząca usera i book
    @Override
    public void lendingBook(Book book, UserLending userLending) {
        lsh.lendingBook(userLending,book);
    }

    @Override
    public Page<Book> findPaginated(int pageNumber, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
             Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNumber -1,pageSize,sort);
        return this.repository.findAll(pageable);
    }

    public void letLendingOneBook (UserLending userLending, Book book){
        repository.findById(book.getId());

    }


    @Override
    public List<Book> search(String keyword) {
        return null;
    }

//    public List<Book> search(String keyword){
//        return repository.searchBookByTitle(keyword);
//    }
}
