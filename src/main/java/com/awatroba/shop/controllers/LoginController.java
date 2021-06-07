package com.awatroba.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Login request controller
 */
@Controller()
public class LoginController extends MyController {
    private ModelAndView model;

    public LoginController() {
        model = new ModelAndView(LOGIN_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE, "");

    }

    /**
     * function to process the log in user request
     *
     * @param error  error message
     * @param logout
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
