package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities;

public class UserNotInDatabaseException extends Exception {

    public UserNotInDatabaseException(String message) {
        super(message);
    }
}
