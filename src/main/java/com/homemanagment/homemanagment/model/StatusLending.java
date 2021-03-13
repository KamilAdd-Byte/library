package com.homemanagment.homemanagment.model;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Embeddable;

@ToString
@Getter
public enum StatusLending {

    LENDING ("wypożyczona"),
    NO_LENDING ("do wypożyczenia");

    private final String description;

    StatusLending(final String description) {
        this.description = description;
    }
}
