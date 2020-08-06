package com.example.courses.repos;

import com.example.courses.entities.Course;
import com.example.courses.entities.Currency;
import com.example.courses.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepo extends JpaRepository<History, Long>, HistoryRepoCust {

  //  @Query(value = "select * from Course where date = ?1 and first_Currency = ?2 and second_Currency = ?3", nativeQuery = true)
  //  List<History> findByFilter(LocalDate data, Long first_Currency, Long second_Currency);

}
