package com.example.courses.controllers;

import com.example.courses.entities.History;
import com.example.courses.repos.HistoryRepo;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("AddHistory")
public class RestMain {

    @Autowired
    HistoryRepo historyRepo;

    @PostMapping
    public void create(@RequestBody String history){
        Gson gson = new Gson();
        History object = gson.fromJson(history, History.class);
        object.setDate();
        historyRepo.save(object);

    }
}
