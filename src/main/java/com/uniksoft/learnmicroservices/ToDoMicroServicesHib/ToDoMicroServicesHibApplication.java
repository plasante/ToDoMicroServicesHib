package com.uniksoft.learnmicroservices.ToDoMicroServicesHib;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos.ToDoDao;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos.UserDao;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.User;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class ToDoMicroServicesHibApplication implements CommandLineRunner {

	@Autowired
	UserDao userDao;

	@Autowired
	ToDoDao toDoDao;

	@Autowired
	EncryptionUtils encryptionUtils;


	public static void main(String[] args) {
		SpringApplication.run(ToDoMicroServicesHibApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Starting to fill the H2 db");

		String encryptedPwd;
		encryptedPwd = encryptionUtils.encrypt("321eRRe!p");
		userDao.save(new User("pierre.lasante@videotron.ca","Pierre Lasante",encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("321eRRe!p");
		userDao.save(new User("judith.spenard@gmail.com","Judith Spenard",encryptedPwd));

		encryptedPwd = encryptionUtils.encrypt("321eRRe!p");
		userDao.save(new User("carole.spenard@gmail.com","Carole Spenard",encryptedPwd));

		toDoDao.save(new ToDo(1, "Learn Microservices", new Date(), "high", "pierre.lasante@videotron.ca"));
		toDoDao.save(new ToDo(2, "Learn Spring boot", new Date(), "low", "pierre.lasante@videotron.ca"));

		toDoDao.save(new ToDo(3, "Feed Animals", new Date(), "high", "judith.spenard@gmail.com"));
		toDoDao.save(new ToDo(4, "Go to take Jim", new Date(), "low", "judith.spenard@gmail.com"));

		toDoDao.save(new ToDo(5, "Bue a new car", new Date(), "high", "carole.spenard@gmail.com"));
		toDoDao.save(new ToDo(6, "Go to Gym", new Date(), "low", "carole.spenard@gmail.com"));

		log.info("Finished filling the H2 db");
	}
}
