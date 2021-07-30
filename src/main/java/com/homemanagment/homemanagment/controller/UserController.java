package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserDao;
import com.homemanagment.homemanagment.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private final UserServiceImpl userService;
    @Autowired
    private final UserDao repository;

    public UserController(final UserServiceImpl service, final UserDao repository) {
        this.userService = service;
        this.repository = repository;
    }
    @GetMapping("/user_info")
    public String getAllUser(Model model){
        model.addAttribute("listAllUser",repository.findAll());
        return "user_info";
    }

    @GetMapping("/user/showNewFormUser")
    public String showNewUserForm(Model model){
        model.addAttribute("user",new UserLending());
        return "new_user";
    }
    @PostMapping("/user/save_user")
    public String saveUser(@ModelAttribute("user") UserLending userLending, Model model){
        userService.addUser(userLending);
        return "redirect:/user_info";
    }
}
