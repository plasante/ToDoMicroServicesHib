package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoDao extends JpaRepository<ToDo, Integer> {
}
