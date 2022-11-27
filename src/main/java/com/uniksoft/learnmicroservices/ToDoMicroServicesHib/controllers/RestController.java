package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.controllers;

import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.ToDo;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.entities.User;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.services.LoginService;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.services.ToDoService;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities.JsonResponseBody;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities.ToDoValidator;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities.UserNotInDatabaseException;
import com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities.UserNotLoggedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    LoginService loginService;

    @Autowired
    ToDoService toDoService;

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";   //"hello" -> hello.jsp -> HTML page (ViewResolver)
    }

    @RequestMapping("/userInOutput")  //jackson-library -> Object.java -> JSON message
    public User giveMeAUser() {
        return new User("pierre@email.com","Pierre","HisPasswordEncrypted");
    }

    @RequestMapping("/todoInInput1")
    public String toDoInInput1(ToDo toDo) {
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput2")
    public String toDoInInput2(@Valid ToDo toDo) {
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    //BindingResult -> Spring object which collects the validation errors
    @RequestMapping("/todoInInput3")
    public String toDoInInput3(@Valid ToDo toDo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "I return the errors in the form I like: " + bindingResult.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput4")
    public String toDoInInput4(ToDo toDo, BindingResult bindingResult) {
        ToDoValidator toDoValidator = new ToDoValidator();
        toDoValidator.validate(toDo, bindingResult);

        if (bindingResult.hasErrors()) {
            return "I return the errors in the form I like: " + bindingResult.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    @RequestMapping("/todoInInput5")
    public String toDoInInput5(@Valid ToDo toDo, BindingResult bindingResult) {
        ToDoValidator toDoValidator = new ToDoValidator();
        toDoValidator.validate(toDo, bindingResult);

        if (bindingResult.hasErrors()) {
            return "I return the errors in the form I like: " + bindingResult.toString();
        }
        return "ToDo Description: " + toDo.getDescription() + ", ToDo Priority: " + toDo.getPriority();
    }

    // HTTP Response => mapped in Java by ResponseEntity

    // this object representing the Http Response has two parts:

    // 1. Header (cookies)

    // 2. Body (Json message)

    // into the Body we want the JSON message of the response: JACKSON LIBRARY

    @RequestMapping("/exampleUrl")
    public ResponseEntity<JsonResponseBody> returnMyStandardResponse() {
        return ResponseEntity.status(HttpStatus.OK).header("jwt").body(new JsonResponseBody(1,null));
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<JsonResponseBody> login(@RequestParam(value = "email") String email, @RequestParam(value = "password") String pwd) {
        //1 success: return a String with login message + JWT in the HEADER of the HTTP REsponse

        //2 return error message
        try {
            Optional<User> userr = loginService.getUserFromDb(email, pwd);
            User user = userr.get();
            String jwt = loginService.createJwt(email, user.getName(), new Date());
            // The only case in which the server sends the JWT to the client in the HEADER of the RESPONSE
            return ResponseEntity.status(HttpStatus.OK).header("jwt", jwt).body(new JsonResponseBody(HttpStatus.OK.value(), "Success! User logged in!"));
        } catch (UserNotInDatabaseException e1 ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new JsonResponseBody(HttpStatus.FORBIDDEN.value(), "Forbidden: " + e1.toString()));
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JsonResponseBody(HttpStatus.BAD_REQUEST.value(), "Bad Request: " + e.toString()));
        }
    }

    @RequestMapping(value = "/showToDos")
    public ResponseEntity<JsonResponseBody> showToDow(HttpServletRequest request) {
        try {
            Map<String, Object> userData = loginService.verifyJwtAndGetData(request);
            return ResponseEntity.status(HttpStatus.OK).body(new JsonResponseBody(HttpStatus.OK.value(), toDoService.getToDos((String) userData.get("email"))));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (UserNotLoggedException e) {
            throw new RuntimeException(e);
        }

    }
}
