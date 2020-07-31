package com.example.courses.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {

    @GetMapping("/")
    public String home( Model model) {
        return "index";
    }

}