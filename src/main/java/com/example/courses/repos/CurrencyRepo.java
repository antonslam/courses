package com.example.courses.repos;

import com.example.courses.entities.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CurrencyRepo extends JpaRepository<Currency, Long> {

    @Query(value = "select * from Currency order by id asc limit 1", nativeQuery = true)
    Currency getOne();

}
