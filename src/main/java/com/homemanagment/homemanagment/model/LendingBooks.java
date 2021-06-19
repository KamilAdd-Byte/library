package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class LendingBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_lending")
    private int id;

    @OneToOne
    @JoinColumn(name = "id")
    private UserLending userLending;

    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;

    @Embedded
    private final Audit audit = new Audit();

    public LendingBooks(UserLending userLending, Book book) {
        this.userLending = userLending;
        this.book = book;
    }

}
