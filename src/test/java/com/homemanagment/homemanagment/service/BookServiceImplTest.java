package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceImplTest {

    @Autowired
    BookService bookService;

    @Autowired
    SessionFactory sessionFactory;

    Session session;

    @BeforeEach
    void setUp(){
       session = sessionFactory.openSession();
       session.beginTransaction();
       session.createQuery("delete Book").executeUpdate();
       session.getTransaction().commit();
    }
    @AfterEach
    void cleanUp(){
        session.close();
    }

    @Test
    @DisplayName("should get list all books")
    void shouldGetListAllBooks() {
        //given
        Book newBook = new Book();
        newBook.setTitle("bar");
        newBook.setAuthor("foo");
        Book newBook2 = new Book();
        newBook2.setTitle("bar2");
        newBook2.setAuthor("foo2");
        Book newBook3 = new Book();
        newBook3.setTitle("bar3");
        newBook3.setAuthor("foo3");
        List<Book>bookList = new ArrayList<>();
        bookList.add(newBook);
        bookList.add(newBook2);
        bookList.add(newBook3);
        //when
        session.beginTransaction();
        for (Book book : bookList) {
            System.out.println(book);
        }
        session.getTransaction().commit();
        List<Book> expected = bookService.allBooks();
        //then
        for (Book tmp : expected) {
            Assertions.assertTrue(bookList.contains(tmp));
        }
    }

    @Test
    @DisplayName("should save book in data base")
    void shouldSaveOneBookOnDataBase() {
        //given
        Book expected = new Book();
        expected.setTitle("Java");
        expected.setAuthor("Programming");
        expected.setIsbn("222323343444");
        expected.setDescription("Example description");
        expected.setLocalization(3);
        expected.setAudit(expected.getAudit());
        //when
        bookService.saveBook(expected);
        Book added = (Book) session.createQuery("from Book book where book.title=:title and book.author=:author and book.isbn=:isbn and book.description=:description and book.localization=:localization")
                .setParameter("title",expected.getTitle())
                .setParameter("author",expected.getAuthor())
                .setParameter("isbn",expected.getIsbn())
                .setParameter("description",expected.getDescription())
                .setParameter("localization",expected.getLocalization())
                .getSingleResult();
        //then
        Assertions.assertEquals(expected,added);
    }

    @Test
    @DisplayName("should find book by id")
    void shouldFindBookById() {
        //given
        Book expected = new Book();
        expected.setTitle("foo");
        expected.setAuthor("bar");
        expected.setIsbn("55434343444");
        expected.setDescription("Example description");
        expected.setLocalization(3);
        expected.setAudit(expected.getAudit());
        //when
        session.beginTransaction();
        bookService.saveBook(expected);
        int id = expected.getId();
        session.getTransaction().commit();
       Book result = bookService.findBookByID(id);
        //then
        Assertions.assertEquals(expected,result);
    }

    @Test
    @DisplayName("should remove book by id number and book")
    void removeBookById() {
        //given
        Book expected = new Book();
        expected.setAuthor("Warczy Młot");
        expected.setTitle("Wyczyny kałamarnicy");
        expected.setLocalization(3);
        expected.setDescription("Powieść fantastyczno naukowa");
        expected.setIsbn("44456765434");
        //when
        bookService.saveBook(expected);
        int id = expected.getId();

        bookService.removeBookById(id,expected);
        //then
        Assertions.assertThrows(Exception.class,() -> bookService.removeBookById(id,expected));

    }
    @Test
    @DisplayName("should update book by id")
    void shouldUpdateBookById(){
        //given
        Book expected = new Book();
        expected.setAuthor("Warczy Młot");
        expected.setTitle("Wyczyny kałamarnicy");
        expected.setLocalization(3);
        expected.setDescription("Powieść fantastyczno naukowa");
        expected.setIsbn("44456765434");
        Book update = new Book();
        update.setAuthor("Młot");
        update.setTitle("Wyczyny");
        update.setLocalization(4);
        update.setDescription("Powieść fantastyczno naukowa");
        update.setIsbn("44456765434");
        //when
        bookService.saveBook(expected);
        bookService.updateBookById(expected.getId());

        int id = expected.getId();

        Book result = bookService.findBookByID(id);
        result.setAuthor("Frank");
        result.setTitle("Barka");
        result.setDescription("Fantastic book");
        result.setLocalization(5);
        result.setIsbn("55556765444");
        //then
        Assertions.assertEquals(expected.getClass(),result.getClass());
    }
}
