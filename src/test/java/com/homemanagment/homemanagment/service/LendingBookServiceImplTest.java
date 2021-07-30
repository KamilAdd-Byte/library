package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.*;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import com.homemanagment.homemanagment.model.type.StatusLending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LendingBookServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;

    @Test
    void allListLending(){

    }

    @Test
    void createNewLending() {
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");
        bookLending.setStatusLending(StatusLending.NO_LENDING);
        bookLending.setCategoryBook(CategoryBook.CRIMINAL);

        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");


        bookService.saveBook(bookLending);
        userService.lendingBook(bookLending,max);


        System.out.println(bookLending);


    }
}
