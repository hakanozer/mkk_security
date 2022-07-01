package com.works.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IncluderController {

    @GetMapping("/navbar")
    public String navbar() {
        return "inc/navbar";
    }

}
