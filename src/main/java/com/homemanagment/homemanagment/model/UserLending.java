package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserLending {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,name = "user_id")
    private int id;

    private String firstName;

    private String lastName;

    private String email;

}


