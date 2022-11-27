package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

//Usage example of Spring Validator
public class ToDoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ToDo.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        ToDo toDo = (ToDo) obj;

        String priority = toDo.getPriority();

        if (!"high".equals(priority) && !"low".equals(priority)) {
            errors.rejectValue("priority", "Priority must be 'high' or 'low'!");
        }
    }
}
