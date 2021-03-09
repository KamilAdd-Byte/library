package com.homemanagment.homemanagment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InfoController {
    @GetMapping("/information")
    public String viewInfo(){
        return "information";
    }
}
