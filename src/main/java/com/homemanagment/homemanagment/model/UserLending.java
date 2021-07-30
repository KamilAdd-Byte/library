package com.homemanagment.homemanagment.model;

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
public class UserLending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "id")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserLending)) return false;
        UserLending userLending = (UserLending) o;
        return id == userLending.id && Objects.equals(firstName, userLending.firstName) && Objects.equals(lastName, userLending.lastName) && Objects.equals(email, userLending.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email);
    }
}


