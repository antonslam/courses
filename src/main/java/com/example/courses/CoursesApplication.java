package com.example.courses;

import com.example.courses.entities.Course;
import com.example.courses.entities.Currency;
import com.example.courses.repos.CourseRepo;
import com.example.courses.repos.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import java.time.LocalDate;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
	}

	@Autowired
	CurrencyRepo currencyRepo;

	@Autowired
	CourseRepo courseRepo;

	@EventListener(ApplicationReadyEvent.class)
	public void AddCurrency(){

		Currency Line = currencyRepo.getOne();
		if (Line == null){
			Get.GetCurrency(currencyRepo);
		}

		Course course = courseRepo.findByDate(LocalDate.now());
		if (course == null){
			Get.GetCourse(courseRepo);
		}

	}
}
