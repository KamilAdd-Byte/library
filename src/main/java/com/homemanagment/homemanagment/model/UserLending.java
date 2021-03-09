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

    @JoinColumn(name = "book_id")
    @OneToMany
    private List<Book> bookUserList;

    public List<Book> addOneBookLending (Book book) {
        bookUserList.add(book);
        return bookUserList;
    }
}
