package com.homemanagment.homemanagment.service.valid;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserLendingValidate {
    Set<UserLending> lendingSet;
    Validator validator;

    @Autowired
    public UserLendingValidate(Validator validator) {
        this.lendingSet = new HashSet<>();
        this.validator = validator;
    }
}
