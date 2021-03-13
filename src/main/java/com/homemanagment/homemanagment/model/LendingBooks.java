package com.homemanagment.homemanagment.model;

import javax.persistence.*;

@Entity
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
}
