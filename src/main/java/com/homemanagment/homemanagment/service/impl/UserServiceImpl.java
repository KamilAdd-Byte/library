package com.homemanagment.homemanagment.service.impl;

import com.homemanagment.homemanagment.model.Book;
import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.BookService;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookService bookService;


    @Override
    @Transactional
    public List<UserLending> allUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public void addUser(UserLending userLending) {
        this.userRepository.save(userLending);
    }

    @Override
    @Transactional
    public void removeUser(int id,UserLending userLending) {
        this.userRepository.deleteById(id);
    }

    @Override
    public UserLending findUserByID(int id) {
        try {
            UserLending find = userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
            return find;
        }catch (NullPointerException e){
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public void addBookToUserList(UserLending userLending,Book book) {
        if (userLending!=null){
           Book borrowed = bookService.findBookByID(book.getId());
           userLending.addBook(borrowed);
        }

    }


}
