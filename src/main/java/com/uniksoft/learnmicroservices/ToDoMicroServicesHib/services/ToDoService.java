package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.services;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;

import java.util.List;

public interface ToDoService {

    List<ToDo> getToDos(String email);

    ToDo addToDo(ToDo toDo);

    void deleteToDo(Integer id);
}
