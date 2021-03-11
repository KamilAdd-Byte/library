//package com.homemanagment.homemanagment.lending;
//
//import com.homemanagment.homemanagment.model.Book;
//import com.homemanagment.homemanagment.model.UserLending;
//import com.homemanagment.homemanagment.repositories.BookDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class Library implements LendingSystem {
//
//    @Autowired
//    private BookDao repository;
//
//    private List<UserLending> userLendingList;
//
//    List<Book> listBooksIsLending = new ArrayList<>();
//
//    @Override
//    public List<Book> allLendingCollection() {
//        for (Book book : listBooksIsLending) {
//            book.isLending();
//        }
//        return new ArrayList<>();
//    }
//
//    @Override
//    public List<UserLending> allLendingUser() {
//        return null;
//    }
//
//    @Override
//    public void lending(UserLending userLending, Book book) {
//        if (!book.isLending()){
//            book.setLending(true);
//            book.setUserLending(userLending);
//            int userLendingId = userLending.getId();
//            listBooksIsLending.add(book);
//            System.out.println("Książke wypożyczył " + userLendingId + " " + userLending.toString());
//        }else if (listBooksIsLending!=null){
//            System.out.println("Książke wypożyczył \" + userLendingId + \" \" + userLending.getLastName()");
//        } else
//            System.out.println("Książka jest wypożyczona");
//            System.out.println(userLending.toString());
//    }
//
//    @Override
//    public void turnBack(UserLending userLending, Book book) {
//    }
//
//
//}
