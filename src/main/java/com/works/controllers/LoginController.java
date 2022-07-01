package com.works.controllers;

import com.works.entities.Admin;
import com.works.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    final UserService uService;
    public LoginController(UserService uService) {
        this.uService = uService;
    }

    @GetMapping("/")
    public String login( Model model ) {
        model.addAttribute("userModel", new Admin());
        String sessionID =  uService.req.getSession().getId();
        System.out.println( "oldSession: " + sessionID );
        return "login";
    }

    @PostMapping("/login")
    public String userLogin(@Valid @ModelAttribute("userModel") Admin user, BindingResult bindingResult, Model model) {
        if ( !bindingResult.hasErrors() ) {
            boolean status = uService.userLogin( user.getEmail(), user.getPassword() );
            if ( status ) {
                return "redirect:/dashboard";
            }
        }
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        uService.logOut();
        return "redirect:/";
    }

}
