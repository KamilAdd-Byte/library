package com.homemanagment.homemanagment.controller;

import com.homemanagment.homemanagment.model.UserLending;
import com.homemanagment.homemanagment.repositories.UserRepository;
import com.homemanagment.homemanagment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

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
    public String showNewUserForm(Model model) {
        UserLending userLending = new UserLending();
        model.addAttribute("user", userLending);
        return "new_user";
    }

    @PostMapping("/user/save_user")
    public String saveUser(@Valid @ModelAttribute("user") UserLending userLending, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<ObjectError> objectErrorList = bindingResult.getAllErrors();
            objectErrorList.forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
            return "new_user";
        } else
            userService.addUser(userLending);
        return "redirect:/user_info";
    }
}
