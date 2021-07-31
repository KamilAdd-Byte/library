package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserLendingServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    Session session;


    @BeforeEach
    void setUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete UserLending").executeUpdate();
        session.getTransaction().commit();
    }
    @AfterEach
    void cleanUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete UserLending").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DisplayName("should add new user")
    void shouldAddNewUser() {
        //given
        UserLending expected = new UserLending();
        expected.setFirstName("bar");
        expected.setLastName("foo");
        expected.setEmail("foo@wp.pl");

        //when
        userService.addUser(expected);

        UserLending added = (UserLending) session.createQuery("from UserLending user where user.firstName=:firstName and " +
                "user.lastName=:lastName and user.email=:email")
                .setParameter("firstName",expected.getFirstName())
                .setParameter("lastName",expected.getLastName())
                .setParameter("email",expected.getEmail())

                .getSingleResult();
        //then
        Assertions.assertEquals(expected.getId(),added.getId());

    }

    @Test
    void removeUser() {
        //TODO implementation remove method
    }

//    @Test
//    void shouldAddBookToUserCollection() {
//        //given
//        Book bookLending = new Book();
//        bookLending.setTitle("First lending");
//        bookLending.setAuthor("Author");
//        bookLending.setIsbn("2234456543");
//
//        UserLending max = new UserLending();
//        max.setEmail("max@wp.pl");
//        max.setFirstName("Max");
//        max.setLastName("Max");
//        //when
//        UserServiceImpl userService = new UserServiceImpl();
//
//        List<Book> booksLendingList = userService.getBooksLendingList();
//
//        assertThat(booksLendingList,hasSize(1));
//    }
    @Test
    @DisplayName("should lending exists book by user")
    void shouldLendingBookByUser(){
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("22344565434");
        bookLending.setCategoryBook(CategoryBook.SAILING);

        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");
        //when

        System.out.println(bookLending);


    }
}
