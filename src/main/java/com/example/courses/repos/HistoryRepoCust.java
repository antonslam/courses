package com.example.courses.repos;

import com.example.courses.entities.History;

import java.time.LocalDate;
import java.util.List;

public interface HistoryRepoCust {
    List<History> findByFilter(String date, Long first_Currency, Long second_Currency);
}
