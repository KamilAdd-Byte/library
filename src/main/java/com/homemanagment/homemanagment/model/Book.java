package com.homemanagment.homemanagment.model;

import com.homemanagment.homemanagment.model.type.BookStatus;
import com.homemanagment.homemanagment.model.type.CategoryBook;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "books")
public class Book implements Comparable<Book> {
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

    @Embedded
    private Audit audit = new Audit();

    @Enumerated(EnumType.STRING)
    private BookStatus bookStatus;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please select category book")
    private CategoryBook categoryBook;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserLending borrower;


    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public UserLending getBorrower() {
        return borrower;
    }

    public void setBorrower(UserLending borrower) {
        this.borrower = borrower;
    }

    @Override
    public int compareTo(Book book) {
        if (localization < book.localization)
            return 1;
        if (localization > book.localization)
            return -1;
        return this.title.compareTo(book.title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && localization == book.localization && title.equals(book.title) && author.equals(book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(description, book.description) && Objects.equals(audit, book.audit) && bookStatus == book.bookStatus && categoryBook == book.categoryBook && borrower.equals(book.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, description, localization, audit, bookStatus, categoryBook, borrower);
    }
}
