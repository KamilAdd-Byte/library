package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class BookLendingServiceTest {

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
        UserLending expected = createNewUser();

        //when
        userService.addUser(expected);

        UserLending added = (UserLending) session.createQuery("from UserLending user where user.firstName=:firstName and " +
                "user.lastName=:lastName and user.email=:email")
                .setParameter("firstName",expected.getFirstName())
                .setParameter("lastName",expected.getLastName())
                .setParameter("email",expected.getEmail())

                .getSingleResult();
        //then
        assertThat(added.getFirstName()).isEqualTo("bar");
        Assertions.assertEquals(expected.getId(),added.getId());

    }

    private UserLending createNewUser() {
        UserLending expected = new UserLending();
        expected.setFirstName("bar");
        expected.setLastName("foo");
        expected.setEmail("foo@wp.pl");
        return expected;
    }

    @Test
    @DisplayName("should remove user by id")
    void shouldRemoveUserById() {
        //given
        UserLending expected = createNewUser();
        //when
        userService.addUser(expected);
        int expectedId = expected.getId();

        userService.removeUser(expectedId,expected);

        //then
        Assertions.assertThrows(Exception.class, () -> userService.removeUser(expectedId,expected));
    }

    @Test
    @DisplayName("should lending exists book by user")
    void shouldLendingBookByUser(){
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("22344565434");
        bookLending.setCategoryBook(CategoryBook.SAILING);

        UserLending max = createNewUser();
        //when

        bookService.saveBook(bookLending);
        userService.addUser(max);
        bookService.lendBook(bookLending.getId(), max);

        System.out.println(bookLending.getBookStatus());



    }
}
