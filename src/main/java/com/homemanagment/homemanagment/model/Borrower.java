package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "borrower")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "user_id")
    private int id;

    @Column(name = "first_name")
    @Size(min = 2, max = 40, message = "Imię nie może zawierać pustego pola. Minimalnie dwa znaki")
    private String firstName;

    @Column(name = "last_name")
    @Size(min = 2, max = 40, message = "Nazwisko nie może zawierać pustego pola. Minimalnie dwa znaki")
    private String lastName;

    private String email;

    @Override
    public String toString() {
        String result = firstName;
        result += " " + lastName;
        result += " e-mail: " + email;
        return result;
    }
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH,CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinColumn(name = "book")
    private Set<Book> books;



    public void addBookToBorrowerList(Book book){
        if (books==null){
            books = new HashSet<>();
        }
        this.books.add(book);
    }

    public void removeBookToBorrowerList(Book book){
        this.books.remove(book);
    }
}


