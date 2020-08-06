package com.example.courses.controllers;

import com.example.courses.entities.Course;
import com.example.courses.entities.Currency;
import com.example.courses.entities.History;
import com.example.courses.repos.CourseRepo;
import com.example.courses.repos.CurrencyRepo;
import com.example.courses.repos.HistoryRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class Main {

    @Autowired
    CurrencyRepo currencyRepo;

    @Autowired
    CourseRepo courseRepo;

    @Autowired
    HistoryRepo historyRepo;




    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("CurrencyList", currencyRepo.findAll());

        Iterable<Course> course = courseRepo.findAll();
        String s = new Gson().toJson(course);

        model.addAttribute("course", s);
        return "home";
    }


    @GetMapping("/history")
    public String history( Model model) {
        model.addAttribute("CurrencyList", currencyRepo.findAll());
        model.addAttribute("HistoryList", historyRepo.findAll());
        return "history";
    }

    @PostMapping("/history")
    public String history(@RequestParam String date, Long first_Currency, Long second_Currency, Model model) {

        model.addAttribute("HistoryList", historyRepo.findByFilter(date, first_Currency, second_Currency));
        model.addAttribute("CurrencyList", currencyRepo.findAll());

        return "history";
    }


}