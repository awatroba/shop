package com.awatroba.shop.controllers;

import com.awatroba.shop.helpers.CreateUserRequest;
import com.awatroba.shop.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Registration request controller
 */

@Controller()
public class RegistrationController {
    private static String CREATE_USER_REQUEST = "userRequest";
    private static String MESSAGE_ERROR = "messageError";
    private static String MESSAGE_SUCCESS = "messageSuccess";
    private ModelAndView model;
    private RegistrationService service;

    @Autowired
    public RegistrationController(RegistrationService service) {
        this.service = service;
        model = new ModelAndView("register");
        model.addObject(CREATE_USER_REQUEST,new CreateUserRequest());
        model.addObject(MESSAGE_ERROR,"");
        model.addObject(MESSAGE_SUCCESS,"");
    }

    /**
     * function to process the create user request
     *
     * @param createUserRequest create user request -requestBody
     * @return ResponseEntity with message -
     * if user added - "success" ,
     * else "error" and error message
     */
    @PostMapping("/register")
    public ModelAndView registration(@ModelAttribute("userRequest") CreateUserRequest createUserRequest) {
        String errorMessage = service.addNewUser(createUserRequest);
        if (errorMessage.equals("")) {
            model.addObject(MESSAGE_SUCCESS,"success");
            model.addObject(MESSAGE_ERROR,"");
            model.setStatus( HttpStatus.OK);
            return model;
        } else {
            model.addObject(MESSAGE_ERROR,errorMessage);
            model.addObject(MESSAGE_SUCCESS,"");
            model.setStatus( HttpStatus.INTERNAL_SERVER_ERROR);
            return model;
        }
    }

    @GetMapping("/register")
    public ModelAndView getRegistrationPage() {
        return model;
    }
}
