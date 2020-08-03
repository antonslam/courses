package com.example.courses.controllers;

import com.example.courses.entities.Users;
import com.example.courses.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Registration {
    @Autowired
    private UsersRepo usersRepo;

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String AddUser(Users user, HttpSession session){
        Users userDB = usersRepo.findByLogin(user.getLogin());

        if (userDB != null){
            session.setAttribute("mess", "User exists");
            return "registration";
        }
        usersRepo.save(user);

        return "redirect:/";

    }
}
