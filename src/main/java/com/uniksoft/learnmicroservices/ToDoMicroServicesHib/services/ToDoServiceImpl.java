package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.services;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.daos.ToDoDao;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoDao toDoDao;

    @Override
    public List<ToDo> getToDos(String email) {
        return toDoDao.findByFkUser(email);
    }

    @Override
    public ToDo addToDo(ToDo toDo) {
        return toDoDao.save(toDo);
    }

    @Override
    public void deleteToDo(Integer id) {
        toDoDao.deleteById(id);
    }
}
