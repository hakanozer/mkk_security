package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login() {
        System.out.println("login call");
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@RequestParam String email, @RequestParam String password, Model model) {
        model.addAttribute("email", email);
        System.out.println( email + " " + password );
        return "login";
    }

}
