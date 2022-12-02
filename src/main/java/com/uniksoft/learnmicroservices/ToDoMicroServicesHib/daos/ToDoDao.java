package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ToDoDao extends JpaRepository<ToDo, Integer> {
    List<ToDo> findByFkUser(String email);

    void deleteById(Integer id);
}
