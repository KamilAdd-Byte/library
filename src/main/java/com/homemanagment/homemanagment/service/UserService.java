package com.homemanagment.homemanagment.service;

import com.homemanagment.homemanagment.model.UserLending;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserLending> getAllUser();
    void addUser(UserLending userLending);
    void removeUser(UserLending userLending);
}
