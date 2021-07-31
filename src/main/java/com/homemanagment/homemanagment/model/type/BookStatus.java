package com.homemanagment.homemanagment.model.type;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public enum BookStatus {

    AVAILABLE("do wypożyczenia"),
    BORROWED ("wypożyczona");

    private final String description;

    BookStatus(final String description) {
        this.description = description;
    }
}
