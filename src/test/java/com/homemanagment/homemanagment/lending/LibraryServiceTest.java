package com.homemanagment.homemanagment.lending;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserLendingDao;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.system.LibraryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryServiceTest {

    @Autowired
    BookService bookService;

    @Autowired
    UserLendingDao userRepo;

    @Autowired
    LibraryService libraryService;

    @BeforeEach
    void setUp(){
       libraryService.removeAllBooks();

    }
    @AfterEach
    void cleanUp(){
        libraryService.removeAllBooks();
    }

    @Test
    @DisplayName("should if new book is not lending")
    void shouldLendingStatusIsFalseToNewBook(){
        //given
        Book newBook = new Book();
        //when and then
        assertFalse(newBook.isLending(),"Check if new book is not lending");
        assertThat(newBook.isLending(),equalTo(false));
        assertThat(newBook.isLending(),is(false));
    }
    @Test
    @DisplayName("should if new collections is empty")
    @Repeat(5)
    void shouldItsNewCollectionsLendingBookIsEmpty(){
        //given
        LibraryService hl = new LibraryService();
        List<Book> listLendingBooks = hl.getListLendingBooks();
        //when & then
        assertThat(listLendingBooks,empty());
        assertThat(listLendingBooks.size(),equalTo(0));
        assertThat(listLendingBooks,hasSize(0));
        assertThat(listLendingBooks,emptyCollectionOf(Book.class));
    }
    @Test
    void shouldAddBookToLendingList(){
        //given
        Book newBook = new Book();
        newBook.setTitle("First lending");
        newBook.setAuthor("Author");
        newBook.setIsbn("2234456543");
        //when
        libraryService.addBookToLendingList(newBook);
        //then
        assertThat(libraryService.getListLendingBooks(),hasSize(1));
    }
    @Test
    @DisplayName("should add five book to list library system lending")
    void shouldAddFiveBookToLendingList(){
        //given
        Book newBook1 = new Book();
        newBook1.setTitle("First lending");
        newBook1.setAuthor("bar");
        newBook1.setIsbn("277788956543");
        Book newBook2 = new Book();
        newBook2.setTitle("Two lending");
        newBook2.setAuthor("foo");
        newBook2.setIsbn("22348456543");
        Book newBook3 = new Book();
        newBook3.setTitle("Three lending");
        newBook3.setAuthor("bar");
        newBook3.setIsbn("2204456543");
        Book newBook4 = new Book();
        newBook4.setTitle("Four lending");
        newBook4.setAuthor("foo");
        newBook4.setIsbn("3244565438");
        Book newBook5 = new Book();
        newBook5.setTitle("Five lending");
        newBook5.setAuthor("foo");
        newBook5.setIsbn("22344526543");
        //when
        libraryService.addBookToLendingList(newBook1);
        libraryService.addBookToLendingList(newBook2);
        libraryService.addBookToLendingList(newBook3);
        libraryService.addBookToLendingList(newBook4);
        libraryService.addBookToLendingList(newBook5);

        //then
        assertThat(libraryService.getListLendingBooks(),hasSize(5));

    }

    @Test
    void shouldLendingOneBookToUser(){
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
        libraryService.lendingBook(max,bookLending);
        //then
        assertTrue(bookLending.isLending());
    }
    @Test
    @DisplayName("should no lending book because book is lending")
    void shouldNoLendingBookToUserBecauseBookIsLending(){
        //given
        Book hamcrest = new Book();
        hamcrest.setTitle("First lending");
        hamcrest.setAuthor("Author");
        hamcrest.setIsbn("2234456543");

        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");

        UserLending bart = new UserLending();
        bart.setEmail("bart@wp.pl");
        bart.setFirstName("Bart");
        bart.setLastName("Bart");
        //when
        libraryService.lendingBook(max,hamcrest);
        libraryService.lendingBook(bart,hamcrest);
        //then

        assertTrue(hamcrest.isLending());
        assertThat(libraryService.getListLendingBooks(),hasSize(1));
    }
    
    
//    @Test
//    @DisplayName("should book lending to user")
//    void shouldLendingBookToUserAndCheckCollectionsNoEmpty(){
//        Book bookLending = new Book();
//        bookLending.setTitle("First lending");
//        bookLending.setAuthor("Author");
//        bookLending.setIsbn("2234456543");
//        Book bookLending2 = new Book();
//        bookLending2.setTitle("Too lending");
//        bookLending2.setAuthor("Rossenberg");
//        bookLending2.setIsbn("44456665435");
//
//        UserLending max = new UserLending();
//        max.setEmail("max@wp.pl");
//        max.setFirstName("Max");
//        max.setLastName("Max");
//        UserLending bart = new UserLending();
//        bart.setEmail("bart@wp.pl");
//        bart.setFirstName("Bart");
//        bart.setLastName("Bart");
//        System.out.println(max);
//        System.out.println(bart);
//
//        LibraryService hl = new LibraryService();
//        //when
//
//        //then
//
//    }
//
    @Test
    @DisplayName("should try recover book from user")
    void shouldTryRecoveredBookFromUser(){
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");

        UserLending bart = new UserLending();
        bart.setEmail("bart@wp.pl");
        bart.setFirstName("Bart");
        bart.setLastName("Bart");

        //when
       libraryService.lendingBook(bart,bookLending);
       libraryService.recoveredBook(bart,bookLending);
        //then
       assertThat(bookLending.isLending(),is(false));

    }

}
