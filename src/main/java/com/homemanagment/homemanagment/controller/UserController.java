package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public UserController(final UserService userService, final UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping("/user_info")
    public String getAllUser(Model model){
        model.addAttribute("listAllBorrower",userService.allUsers());
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
        model.addAttribute("userLending",userLending);
        return "redirect:/user_info";
    }
}
