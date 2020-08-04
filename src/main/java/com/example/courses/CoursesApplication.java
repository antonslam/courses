package com.example.courses;

import com.example.courses.entities.Currency;
import com.example.courses.repos.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context =SpringApplication.run(CoursesApplication.class, args);
	}

	@Autowired
	CurrencyRepo currencyRepo;

	@EventListener(ApplicationReadyEvent.class)
	public void AddCurrency() throws IOException, SAXException, ParserConfigurationException {
		Currency Line = currencyRepo.getOne();
		if (Line == null){
			Get.GetCurrency(currencyRepo);
		}
	}
}
