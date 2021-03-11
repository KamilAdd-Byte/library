package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLending {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int id;

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @Email
    private String email;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Book> collectionLendingBook = new HashSet<>();

    public void addBookToUserCollection(Book book){
        try {
            collectionLendingBook.add(book);
            System.out.println("Dodano do kolekcji użytkownika");
        } catch (NullPointerException e){
            e.printStackTrace();
            System.err.println("Nie dodano do kolekcji!");
        }
    }

    public void removeBookToUserCollection(Book book){
        try {
            collectionLendingBook.remove(book);
            System.out.println("Książka usunięta z kolekcji użytkownika");
        }catch (NullPointerException e){
            e.printStackTrace();
            System.err.println("Nie udało się usunac z kolekcji");
        }
    }

    public void getSizeCollectionLendingBooks(){
        for (int i = 0; i < collectionLendingBook.size(); i++) {
            System.out.println("Książek wypożyczonych: " + collectionLendingBook.size());
        }
    }

}


