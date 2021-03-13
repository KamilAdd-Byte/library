package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

class UserLendingServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    void allUsers() {
    }

    @Test
    void addUser() {
    }

    @Test
    void removeUser() {
    }

    @Test
    void shouldAddBookToUserCollection() {
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");

        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");
        //when
        UserServiceImpl userService = new UserServiceImpl();
        userService.addBookToUserCollection(bookLending);
        List<Book> booksLendingList = userService.getBooksLendingList();

        assertThat(booksLendingList,hasSize(1));
    }
    @Test
    void lendingBook(){
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");
        System.out.println(bookLending);
        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");
        //when
        userService.lendingBook(bookLending);
        System.out.println(bookLending);


    }
}
