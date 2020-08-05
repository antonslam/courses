package com.example.courses.repos;

import com.example.courses.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface CourseRepo extends JpaRepository<Course, Long> {
    Course findByDate(LocalDate data);
}
