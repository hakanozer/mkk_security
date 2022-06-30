package com.works.controllers;

import com.works.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class LoginController {

    @GetMapping("/")
    public String login( Model model ) {
        model.addAttribute("userModel", new User());
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@Valid @ModelAttribute("userModel") User user, BindingResult bindingResult, Model model) {
        model.addAttribute("email", user.getEmail());
        System.out.println( "Form submit : "+  user.getEmail() + " " + user.getPassword() );
        return "login";
    }

}
