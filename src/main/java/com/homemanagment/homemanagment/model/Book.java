package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book implements Comparable<Book>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, max = 40,message = "Tytuł nie może być pusty")
    @Column(name = "title",nullable = false)
    private String title;

    @Size(min = 2, max = 40,message = "Autor nie może zawierać pustego pola. Minimalnie dwa znaki")
    @Column(name = "author",nullable = false)
    private String author;

    @Size(min = 10, max = 13,message = "Numer ISBN nie może zawierać pustego pola. Minimalnie 10 znaków")
    @Column(name = "isbn")
    private String isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "localization")
    private int localization;

    @Embedded
    private Audit audit = new Audit();

    @Column(name = "lending")
    private boolean lending;

    @JoinColumn(name = "id_user")
    @ManyToOne(cascade = CascadeType.DETACH)
    private UserLending userLending;

    public Book(boolean lending) {
        this.lending = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id && localization == book.localization && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(description, book.description) && Objects.equals(audit, book.audit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, description, localization, audit);
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
