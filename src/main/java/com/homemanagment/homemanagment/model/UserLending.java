package com.homemanagment.homemanagment.model;

import com.homemanagment.homemanagment.model.type.BookStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "users")
public class UserLending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "user_id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(columnDefinition = "all_books")
    private Set<Book> books;


    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBookToUserCollection(Book book){
        if (books == null){
            books = new TreeSet<>();
        }
        this.books.add(book);
        book.setBookStatus(BookStatus.BORROWED);
    }

    public void removeBookToUserCollection(Book book){
        if (books == null){
            books = new TreeSet<>();
        }
        this.books.remove(book);
        book.setBookStatus(BookStatus.AVAILABLE);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLending)) return false;
        UserLending that = (UserLending) o;
        return id == that.id && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(books, that.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, books);
    }
}


