package com.homemanagment.homemanagment.service;


import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;


@Service
public interface UserService {
    void addUser(UserLending userLending);
    void removeUser(UserLending userLending);
}
