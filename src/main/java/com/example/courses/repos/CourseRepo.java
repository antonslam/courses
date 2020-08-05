package com.example.courses.repos;

import com.example.courses.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query(value = "select * from Course where date = ?1 order by id asc limit 1", nativeQuery = true)
    Course OneBydate(LocalDate data);
}
