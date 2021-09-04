package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.Borrower;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserServiceImplTest {
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
        session.createQuery("delete Book").executeUpdate();
        session.createQuery("delete Borrower").executeUpdate();
        session.getTransaction().commit();
    }
    @AfterEach
    void cleanUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Book").executeUpdate();
        session.createQuery("delete Borrower").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Test
    @DisplayName("should add new user")
    void shouldAddNewUser() {
        //given
        Borrower expected = createNewUser();

        //when
        userService.addUser(expected);

        Borrower added = (Borrower) session.createQuery("from Borrower user where user.firstName=:firstName and " +
                        "user.lastName=:lastName and user.email=:email")
                .setParameter("firstName",expected.getFirstName())
                .setParameter("lastName",expected.getLastName())
                .setParameter("email",expected.getEmail())

                .getSingleResult();

        //then

        assertThat(added.getFirstName()).isEqualTo("Marceli");
        Assertions.assertEquals(expected.getId(),added.getId());
    }

    private Borrower createNewUser() {
        Borrower expected = new Borrower();
        expected.setFirstName("Marceli");
        expected.setLastName("Szpak");
        expected.setEmail("marceli@szpak.pl");

        return expected;
    }

    private Book createNewBook() {
        //given
        Book expected = new Book();
        expected.setTitle("Java");
        expected.setAuthor("Programming");
        expected.setIsbn("222323343444");
        expected.setDescription("Example description");
        expected.setLocalization(3);
        expected.setBookStatus(BookStatus.AVAILABLE);
        expected.setCategoryBook(CategoryBook.SAILING);
        return expected;
    }
    @Test
    @DisplayName("should remove user by id")
    void shouldRemoveUserById() {
        //given
        Borrower expected = createNewUser();
        //when
        userService.addUser(expected);
        int expectedId = expected.getId();

        userService.removeUser(expectedId,expected);

        //then
        Assertions.assertThrows(Exception.class, () -> userService.removeUser(expectedId,expected));
    }
}
