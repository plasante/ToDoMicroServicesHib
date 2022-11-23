package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserDao extends JpaRepository<User, String> {
    Optional<User> findUserByEmail(String email);

    //@Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    //Optional<User> findUserByTheEmail(String email);

    //User findOne(String email);
}
