package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;


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
    @DisplayName("should lending exists book by user")
    void shouldLendBookByUser(){
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
        bookService.lendBook(addedBook.getId(), max);
        Book borrowedBook = bookService.findBookByID(addedBook.getId());


        assertNotNull(borrowedBook.getBorrower().getId());
        assertEquals(max.getFirstName(),borrowedBook.getBorrower().getFirstName());
        assertEquals(max.getLastName(),borrowedBook.getBorrower().getLastName());
    }
    @Test
    @DisplayName("should give back book")
    void shouldGiveBackBook(){
        //given
        Book addedBook = createNewBook();

        UserLending max = createNewUser();
        userService.addUser(max);

        //when
        bookService.lendBook(addedBook.getId(), max);
        Book borrowedBook = bookService.findBookByID(addedBook.getId());

        bookService.giveBackBook(borrowedBook.getId(),max);


        //then
          assertTrue(addedBook.getBookStatus().equals(BookStatus.AVAILABLE));
          assertNotEquals(addedBook.getBookStatus(),borrowedBook.getBookStatus());
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

        Book borrowed = bookService.lendBook(bookId, userLending);

        userService.addBookToUserList(userLending,borrowed);

        System.out.println(userLending.getBooks());


    }
}
