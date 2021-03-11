package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserLendingDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserLendingDao repository;


    @Override
    public void addUser(UserLending userLending) {
        this.repository.save(userLending);
    }

    @Override
    public void removeUser(UserLending userLending) {
        this.repository.delete(userLending);
    }
}
