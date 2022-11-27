package com.uniksoft.learnmicroservices.ToDoMicroServicesHib.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class JsonResponseBody {
    private int server;
    private Object response;

}

// hppt_response => java object ResponseEntity<JsonResponseBody>

// header (jwt)

// body => HTML page or a JsonMessage(JsonResponseBody(int server, Object response))


