package com.homemanagment.homemanagment.model;

import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_book", nullable = false)
    private int id;

    @Size(min = 1, max = 40, message = "Tytuł nie może być pusty")
    @Column(name = "title", nullable = false)
    private String title;

    @Size(min = 2, max = 40, message = "Autor nie może zawierać pustego pola. Minimalnie dwa znaki")
    @Column(name = "author", nullable = false)
    private String author;

    @Size(min = 10, max = 13, message = "Numer ISBN nie może zawierać pustego pola. Minimalnie 10 znaków")
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "localization")
    private int localization;

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please select category book")
    private CategoryBook categoryBook;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserLending borrower;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocalization() {
        return localization;
    }

    public void setLocalization(int localization) {
        this.localization = localization;
    }


    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public CategoryBook getCategoryBook() {
        return categoryBook;
    }

    public void setCategoryBook(CategoryBook categoryBook) {
        this.categoryBook = categoryBook;
    }

    public UserLending getBorrower() {
        return borrower;
    }

    public void setBorrower(UserLending userLending){
        this.borrower = userLending;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && localization == book.localization && title.equals(book.title) && author.equals(book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(description, book.description) && bookStatus == book.bookStatus && categoryBook == book.categoryBook;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, description, localization, bookStatus, categoryBook);
    }

    @Override
    public String toString() {
        String result = " Title: " + title;
        result+= " Author: " + author;
        result+= " Category: " + categoryBook;
        result+= " Isbn: " + isbn;
        result+= " Borrower: " + borrower;
        return result;
    }
}
