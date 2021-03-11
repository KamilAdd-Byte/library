package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Library {
    @Id
    private int id;
    @OneToOne
    @JoinColumn(name = "id_user")
    private UserLending userLending;
    @OneToOne
    @JoinColumn(name = "id_book")
    private Book book;
}
