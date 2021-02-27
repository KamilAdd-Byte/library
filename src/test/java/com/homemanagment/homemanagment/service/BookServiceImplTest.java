package com.homemanagment.homemanagment.service;


import com.homemanagment.homemanagment.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
       session.createQuery("delete Book ").executeUpdate();
       session.getTransaction().commit();
    }
    @AfterEach
    void cleanUp(){
        session.close();
    }

    @Test
    void allBooks() {
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
    void removeBook() {
    }

    @Test
    void findBookById() {
    }
}
