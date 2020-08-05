package com.example.courses.controllers;

import com.example.courses.entities.Course;
import com.example.courses.repos.CourseRepo;
import com.example.courses.repos.CurrencyRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class Main {

    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    CourseRepo courseRepo;

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("CurrencyList", currencyRepo.findAll());

        Iterable<Course> course = courseRepo.findAll();
        String s = new Gson().toJson(course);

        model.addAttribute("course", s);
        return "home";
    }

}