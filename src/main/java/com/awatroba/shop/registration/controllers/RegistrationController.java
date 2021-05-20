package com.awatroba.shop.registration.controllers;

import com.awatroba.shop.helpers.CreateUserRequest;
import com.awatroba.shop.helpers.Response;
import com.awatroba.shop.registration.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Angelika
 */

@RestController
public class RegistrationController {
    private RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/registration")
    public ResponseEntity<Object> registration(@RequestBody CreateUserRequest createUserRequest) {
        String errorMessage = service.addNewUser(createUserRequest);
       if(errorMessage.equals("")){
           Response<CreateUserRequest>  response =
                   new Response<CreateUserRequest>("success", createUserRequest);
           return new ResponseEntity<Object>(response, HttpStatus.OK);
       } else {
           Response<String> response =
                   new Response<String>("error", errorMessage);
           return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
       }
    }
}
