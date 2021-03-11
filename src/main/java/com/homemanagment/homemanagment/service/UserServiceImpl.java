package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserLendingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserLendingRepository repository;

    @Override
    public List<UserLending> getAllUser() {
        return repository.findAll();
    }

    @Override
    public void addUser(UserLending userLending) {
        this.repository.save(userLending);
    }

    @Override
    public void removeUser(UserLending userLending) {
        this.repository.delete(userLending);
    }
}
