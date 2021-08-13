package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.*;

@Entity
@ToString
@Getter
@Setter
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "all_books")
    private Set<Book> books;


    public void addBook(Book book){
        if (books == null){
            books = new HashSet<>();
        }
        books.add(book);
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


