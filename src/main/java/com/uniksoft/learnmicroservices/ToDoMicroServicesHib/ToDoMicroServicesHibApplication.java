package com.uniksoft.learnmicroservices.ToDoMicroServicesHib;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos.ToDoDao;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class ToDoMicroServicesHibApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;

	@Autowired
	ToDoDao toDoDao;
	
	public static void main(String[] args) {
		SpringApplication.run(ToDoMicroServicesHibApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting to fill the H2 db");

		log.info("Finished filling the H2 db");
	}
}
