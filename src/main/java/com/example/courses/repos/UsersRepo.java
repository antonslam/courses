package com.example.courses.repos;

import com.example.courses.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Long> {
    Users findByLogin(String login);
}
