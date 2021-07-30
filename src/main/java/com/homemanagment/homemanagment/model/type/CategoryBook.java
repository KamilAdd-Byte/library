package com.homemanagment.homemanagment.model.type;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum CategoryBook {
    CRIMINAL("Kryminał"),
    PSYCHOLOGIST("Psychologia"),
    PROGRAMMING("Programowanie"),
    FANTASTIC("Fantastyka"),
    SAILING("Żeglarstwo"),
    ROMANTIC("Romans"),
    POETRY("Poezja"),
    EDUCATION("Pedagogika, edukacja"),
    KIDS("Dla dzieci"),
    OTHERS("Inne");

    public String description;

    CategoryBook(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
