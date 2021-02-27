package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Component
public class BookServiceValidate {
    Set<Book>bookSet;
    Validator validator;

    @Autowired
    public BookServiceValidate(Validator validator) {
        this.bookSet = new HashSet<>();
        this.validator = validator;
    }
}
