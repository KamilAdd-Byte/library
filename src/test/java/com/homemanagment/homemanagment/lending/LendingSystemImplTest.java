package com.homemanagment.homemanagment.lending;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.service.BookService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class LendingSystemImplTest {

    @Autowired
    BookService bookService;

    UserLending user;

//    @Autowired
//    SessionFactory sessionFactory;
//
//    Session session;
//
//    @BeforeEach
//    void setUp() {
//        session = sessionFactory.openSession();
//        session.beginTransaction();
//        session.createQuery("delete Book").executeUpdate();
//        session.createQuery("delete UserLending").executeUpdate();
//        session.getTransaction().commit();
//    }
//
//    @AfterEach
//    void tearDown() {
//        session.close();
//    }

    @Test
    @DisplayName("should if new book is not lending")
    void checkLendingStatusIsFalseToNewBook(){
        //given
        Book newBook = new Book();
        //when
        assertFalse(newBook.isLending(),"Check if new book is not lending");
        assertThat(newBook.isLending(),equalTo(false));
        assertThat(newBook.isLending(),is(false));
    }

    @Test
    void lending() {
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");
        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("User");
        //when
        bookService.saveBook(bookLending);
        assertFalse(bookLending.isLending(),"Is lending?");
    }

    @Test
    void turnBack() {
    }
}
