package com.homemanagment.homemanagment.system;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserLendingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryService implements LendingSystem {

    private List<Book> listLendingBooks = new ArrayList<>();

    private List<UserLending> userLendingList = new ArrayList<>();

    @Autowired
    private UserLendingDao userRepo;

    public List<Book> getListLendingBooks() {
        System.out.println("Rozmiar kolekcji: " + listLendingBooks.size());
        return listLendingBooks;
    }

    public void setListLendingBooks(List<Book> listLendingBooks) {
        this.listLendingBooks = listLendingBooks;
    }

    public UserLendingDao getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserLendingDao userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public void addBookToLendingList(Book book) {
        checkBookIsLending(book);
        if (!book.isLending()) {
            listLendingBooks.add(book);
        } else
            System.out.println("Nie można wypożyczyć!");
    }

    @Override
    public boolean checkBookIsLending(Book book) {
        if (!book.isLending()) {
            System.out.println("Książka dostepna");
            return true;
        } else {
            System.out.println("Brak możliwości wypożyczenia!!");
        }
        return false;
    }

    public void saveNewUser(UserLending user) {
        try {
            userRepo.save(user);
            System.out.println("Add user to data base");
        } catch (NullPointerException | IndexOutOfBoundsException exception) {
            exception.getStackTrace();
            exception.getMessage();
        }
    }

    @Override
    public void lendingBook(UserLending userLending, final Book book) {
        if (!checkBookIsLending(book)) {
            System.err.println("Status wypożyczenia: "+book.isLending());
        } else {
            userRepo.save(userLending);
            operationSetParametersBook(userLending, book);
            operationSetParametersUser(userLending, book);
            System.out.println("Status wypożyczenia: "+book.isLending());
        }

    }

    private void operationSetParametersUser(UserLending userLending, Book book) {
        userLending.addBookToUserCollection(book);
        userLending.getSizeCollectionLendingBooks();
    }

    private void operationSetParametersBook(UserLending userLending, Book book) {
        book.setUserLending(userLending);
        addBookToLendingList(book);
        book.setLending(true);
    }

    @Override
    public void recoveredBook(UserLending userLending, Book book) {
        if (!checkBookIsLending(book)){
            book.setLending(false);
            book.setUserLending(null);
            userLending.removeBookToUserCollection(book);
            System.out.println("Status wypożyczenia: "+ book.isLending());
        }else {
            System.err.println("Błąd zwracania!");
        }

    }

    @Override
    public void removeAllBooks() {
        getListLendingBooks().removeAll(listLendingBooks);
    }


}
