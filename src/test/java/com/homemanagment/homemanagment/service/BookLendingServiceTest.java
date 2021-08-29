package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
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
        session.createQuery("delete Book").executeUpdate();
        session.createQuery("delete UserLending").executeUpdate();
        session.getTransaction().commit();
    }
    @AfterEach
    void cleanUp(){
        session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("delete Book").executeUpdate();
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
        assertThat(added.getFirstName()).isEqualTo("Marceli");
        Assertions.assertEquals(expected.getId(),added.getId());

    }

    private UserLending createNewUser() {
        UserLending expected = new UserLending();
        expected.setFirstName("Marceli");
        expected.setLastName("Szpak");
        expected.setEmail("marceli@szpak.pl");

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
    @DisplayName("should lending exists book by new user")
    void shouldLendBookByNewUser(){
        //given
        Book book = new Book();
        book.setTitle("First lending");
        book.setAuthor("Author");
        book.setIsbn("22344565434");
        book.setDescription("Lending book");
        book.setCategoryBook(CategoryBook.SAILING);
        Book addedBook = bookService.saveBook(book);

        UserLending max = createNewUser();
        userService.addUser(max);


        //when
        int addedBookId = addedBook.getId();
        Book lendBook = bookService.lendBook(addedBookId, max);
        lendBook.setBorrower(max);
//        max.addBookToUserCollection(lendBook);

        log.info("Max : " + max.toString() + "Collection: " + max.getBooks());
        log.info("Book : " + lendBook.toString());

        assertThat(lendBook.getBorrower()).isEqualTo(max);

    }
    @Test
    @DisplayName("should give back book")
    void shouldGiveBackBook(){
        //given
        Book addedBook = createNewBook();

        UserLending max = createNewUser();
        userService.addUser(max);

        //when
        Book lendBook = bookService.lendBook(addedBook.getId(), max);
        int lendBookId = lendBook.getId();

        Book giveBackBook = bookService.giveBackBook(lendBookId, max);


        //then
        assertNotNull(lendBook);
        assertEquals(giveBackBook.getBookStatus(), BookStatus.AVAILABLE);
        log.info("Give back status and info: " + giveBackBook.toString());


    }

    private Book createNewBook() {
        Book book = new Book();
        book.setTitle("First lending");
        book.setAuthor("Author");
        book.setIsbn("22344565434");
        book.setDescription("Lending book");
        book.setCategoryBook(CategoryBook.SAILING);
        return bookService.saveBook(book);
    }
    @Test
    @DisplayName("should add book to set user lending")
    void shouldAddBookToSetUserLending() {
        //given
        Book book = createNewBook();
        UserLending userLending = createNewUser();

        bookService.saveBook(book);
        userService.addUser(userLending);

        //when
        int bookId = book.getId();

        userLending.addBookToUserCollection(book);

        assertThat(userLending.getBooks()).hasSize(1);

    }
}
