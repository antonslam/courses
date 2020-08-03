package com.example.courses.service;

import com.example.courses.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {
    @Autowired
    private UsersRepo usersRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return usersRepo.findByLogin(s);
    }
}
