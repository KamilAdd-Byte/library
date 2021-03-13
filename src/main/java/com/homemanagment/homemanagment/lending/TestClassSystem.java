//package com.homemanagment.homemanagment.lending;
//import com.homemanagment.homemanagment.model.Book;
//import com.homemanagment.homemanagment.model.UserLending;
//import com.homemanagment.homemanagment.service.BookServiceImpl;
//import com.homemanagment.homemanagment.service.UserServiceImpl;
//import com.homemanagment.homemanagment.system.LibraryService;
//
//public class TestClassSystem {
//
//    public static void main(String[] args) {
//        System.out.println("-------------UserLending----------------");
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
//        System.out.println("-------------Book----------------");
//        Book bookLending = new Book();
//        bookLending.setTitle("First lending");
//        bookLending.setAuthor("Author");
//        bookLending.setIsbn("2234456543");
//
//        Book bookLending2 = new Book();
//        bookLending2.setTitle("Too lending");
//        bookLending2.setAuthor("Rossenberg");
//        bookLending2.setIsbn("44456665435");
//
//        System.out.println(bookLending);
//        System.out.println(bookLending2);
//
//        System.out.println("-------------Library-Lending---------------");
//        LibraryService ls = new LibraryService();
//        ls.lendingBook(max,bookLending);
//        System.out.println(bookLending);
//
//        System.out.println("-------------Library-List_Lending---------------");
////        List<Book> books = hl.getLendingBookList();
////        for (Book book : books) {
////            System.out.println(book);
////        }
//        System.out.println("-------------Ponowne wypożyczenie---------------");
////        hl.lendingBook(max,bookLending);
//
////        System.out.println("-------------Zwracanie książki---------------");
////        hl.recoveredBook(max,bookLending);
////        System.out.println(bookLending);
////        List<Book> books1 = hl.getLendingBookList();
////        for (Book book1 : books) {
////            System.out.println(book1);
////        }
//    }
//}
