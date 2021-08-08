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

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
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
}
