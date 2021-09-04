package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.Borrower;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

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

    @Test
    @DisplayName("should borrowed exists one book by new user")
    void shouldBorrowedOneBookByNewUser(){
        //given
        Book addedBook = createBook();

        Borrower max = createNewUser();
        userService.addUser(max);


        //when
        int addedBookId = addedBook.getId();
        Book lendBook = bookService.lendBook(addedBookId, max);



        log.info("Book : " + lendBook.toString());
        log.info("User: " + max.getBooks());
        assertThat(max.getFirstName()).isEqualTo("Marceli");


    }
    @Test
    @DisplayName("should borrowed exists two book by one user")
    void shouldBorrowedTwoBookByOneUser(){
        //given
        Book addedBook = createBook();
        Book addedBook2 = createBook2();
        Borrower max = createNewUser();
        userService.addUser(max);


        //when
        int addedBookId = addedBook.getId();
        int addedBookId2 = addedBook2.getId();
        Book lendBook = bookService.lendBook(addedBookId, max);
        Book lendBook1 = bookService.lendBook(addedBookId2, max);


log.info("Max : " + max.toString() + " <<Collection>>: " + max.getBooks() + " <<Size>>: " + max.getBooks().size());
//       log.info("Book : " + lendBook.getBorrower());
//
       assertThat(lendBook.getBorrower().getFirstName()).isEqualTo("Marceli");
       assertThat(max.getBooks()).hasSize(2);


    }

    private Book createBook2() {
        Book book = new Book();
        book.setTitle("Two lending");
        book.setAuthor("Author two");
        book.setIsbn("43344565434");
        book.setDescription("Two Lending book");
        book.setCategoryBook(CategoryBook.PROGRAMMING);
        return bookService.saveBook(book);
    }

    private Book createBook() {
        Book book = new Book();
        book.setTitle("First lending");
        book.setAuthor("Author");
        book.setIsbn("22344565434");
        book.setDescription("Lending book");
        book.setCategoryBook(CategoryBook.SAILING);
        return bookService.saveBook(book);
    }


    @Test
    @DisplayName("should give back book")
    void shouldGiveBackBook(){
        //given
        Book addedBook = createNewBook();

        Borrower max = createNewUser();
        userService.addUser(max);

        //when
//        bookService.lendBook(addedBook.getId(), max);
//        int lendBookId = lendBook.getId();
//
//        Book giveBackBook = bookService.giveBackBook(lendBookId, max);
//
//
//        //then
//        assertNotNull(lendBook);
//        assertEquals(giveBackBook.getBookStatus(), BookStatus.AVAILABLE);
//        log.info("Give back status and info: " + giveBackBook.toString());


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
}
