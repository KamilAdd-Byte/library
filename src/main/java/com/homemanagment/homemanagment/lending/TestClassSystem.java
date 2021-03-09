package com.homemanagment.homemanagment.lending;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;

import java.util.List;

public class TestClassSystem {
    public static void main(String[] args) {
        System.out.println("-------------User----------------");
        UserLending max = new UserLending();
        max.setEmail("max@wp.pl");
        max.setFirstName("Max");
        max.setLastName("Max");

        System.out.println(max);

        System.out.println("-------------Book----------------");
        Book bookLending = new Book();
        bookLending.setTitle("First lending");
        bookLending.setAuthor("Author");
        bookLending.setIsbn("2234456543");
        Book bookLending2 = new Book();
        bookLending2.setTitle("Too lending");
        bookLending2.setAuthor("Rossenberg");
        bookLending2.setIsbn("44456665435");

        System.out.println(bookLending);
        System.out.println(bookLending2);

        System.out.println("-------------Library-Lending---------------");
        Library library = new Library();
        library.lending(max,bookLending);
        System.out.println("-------------Library-List_Lending---------------");

        List<Book> allLendingCollection = library.allLendingCollection();
        System.out.println(allLendingCollection);

        System.out.println(bookLending);

        System.out.println("-------------User-Bart---------------");

        UserLending bart = new UserLending();
        bart.setEmail("bart@wp.pl");
        bart.setFirstName("Bart");
        bart.setLastName("Bart");

        System.out.println(bart);

        System.out.println("-------------User-Bart-lending-------------");
        library.lending(bart,bookLending2);

        System.out.println("-------------Library-List_Lending---------------");
        library.allLendingCollection();
        System.out.println(allLendingCollection);
    }
}
