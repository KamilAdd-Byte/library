package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.List;
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

    @Size(min = 2,max = 30)
    private String firstName;

    @Size(min = 2,max = 30)
    private String lastName;

    @Email
    private String email;

    @JoinColumn(name = "book_list_id")
    @OneToMany
    private List<Book> bookUserList;

    public List<Book> addOneBookLending (Book book) {
        bookUserList.add(book);
        return bookUserList;
    }

    public void lendingBook(Book book){
        try {
            this.bookUserList.add(book);
            System.out.println("Ksiązke udało sie wypożyczyć i dopisać do listy użytkownika");
        }catch (NullPointerException e){
            e.printStackTrace();
            System.out.println("Dodanie do listy nieudane");
        }

    }
}
