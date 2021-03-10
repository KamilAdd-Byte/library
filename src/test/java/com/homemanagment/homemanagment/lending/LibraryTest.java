package com.homemanagment.homemanagment.lending;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.system.HomeLibrary;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.Repeat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {

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
        HomeLibrary hl = new HomeLibrary();
        //when & then
        assertThat(hl.getLendingBookList(),empty());
        assertThat(hl.getLendingBookList().size(),equalTo(0));
        assertThat(hl.getLendingBookList(),hasSize(0));
        assertThat(hl.getLendingBookList(),emptyCollectionOf(Book.class));
    }

    @Test
    @DisplayName("should book lending to user")
    void shouldLendingBookToUserAndCheckCollectionsNoEmpty(){
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");
        Book bookLending2 = new Book();
        bookLending2.setTitle("Too lending");
        bookLending2.setAuthor("Rossenberg");
        bookLending2.setIsbn("44456665435");

        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");
        UserLending bart = new UserLending();
        bart.setEmail("bart@wp.pl");
        bart.setFirstName("Bart");
        bart.setLastName("Bart");
        System.out.println(max);
        System.out.println(bart);

        HomeLibrary hl = new HomeLibrary();
        //when
        hl.lendingBook(max,bookLending);
        //then
        assertTrue(bookLending.isLending());
        assertThat(hl.getLendingBookList(),hasSize(1));
    }

    @Test
    @DisplayName("should if book is lending to recovered is no lending")
    void shouldLendingStatusIsFalseToRecoveredBook(){
        //given
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");

        UserLending bart = new UserLending();
        bart.setEmail("bart@wp.pl");
        bart.setFirstName("Bart");
        bart.setLastName("Bart");

        HomeLibrary hl = new HomeLibrary();

        //when
        hl.lendingBook(bart,bookLending);
        hl.recoveredBook(bart,bookLending);

        //then
        assertFalse(bookLending.isLending());
        assertThat(hl.getLendingBookList(),hasSize(0));
    }

}
