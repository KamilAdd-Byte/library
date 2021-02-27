package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Book implements Comparable<Book>{
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 1, max = 20,message = "Tytuł nie może być pusty")
    @Column(name = "title",nullable = false,unique = true)
    @NotBlank
    private String title;

    @Size(min = 2, max = 20,message = "Autor nie może zawierać pustego pola. Minimalnie dwa znaki")
    @Column(name = "author",nullable = false)
    @NotBlank
    private String author;

    @Size(min = 10, max = 13,message = "Numer ISBN nie może zawierać pustego pola. Minimalnie 10 znaków")
    @Column(name = "isbn")
    @NotBlank
    private String isbn;

    @Column(name = "description")
    private String description;

    @Column(name = "localization")
    private int localization;

    @Embedded
    private Audit audit = new Audit();

    @JoinColumn(name = "id_user_rental")
    @OneToOne
    private UserBookRental userBookRental;

    @JoinColumn(name = "id_bookRental")
    @ManyToOne
    private BookRental bookRental;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id && localization == book.localization && title.equals(book.title) && author.equals(book.author) && isbn.equals(book.isbn) && description.equals(book.description) && audit.equals(book.audit);
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
