package com.awatroba.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Login request controller
 */
@Controller()
public class LoginController {
    private static String MESSAGE_ERROR = "messageError";
    private static String LOGIN_MESSAGE_ERROR = "Your username or password are invalid.";
    private static String LOGGED_OUT_SUCC_MESSAGE = "You have been logged out successfully.";
    private static String MESSAGE = "message";

    private ModelAndView model;

    public LoginController() {
        model = new ModelAndView("login");
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE, "");

    }

    /**
     * function to process the log in user request
     *
     * @return ModelAndView with message
     */
    @RequestMapping("/login")
    public ModelAndView login(String error, String logout) {
        if (error != null)
            model.addObject(MESSAGE_ERROR, LOGIN_MESSAGE_ERROR);

        if (logout != null)
            model.addObject(MESSAGE, LOGGED_OUT_SUCC_MESSAGE);
        return model;
    }

}
