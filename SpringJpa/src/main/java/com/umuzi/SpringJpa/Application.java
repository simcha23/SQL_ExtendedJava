package com.umuzi.SpringJpa;

import com.umuzi.SpringJpa.controller.MainController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
	@Autowired
	private MainController mainController;

	@Override
	public void run(String[] args) throws IOException {

//		mainController.getAllEmployees();
	}

}
