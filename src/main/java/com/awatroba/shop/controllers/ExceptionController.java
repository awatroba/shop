package com.awatroba.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Exception controller
 */
@Controller()
public class ExceptionController extends MyController {

    private ModelAndView model;

    @Autowired
    public ExceptionController() {
        model = new ModelAndView(ACCESS_DENIED_CART_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, false);
    }

    /**
     * function get page for access denied
     *
     * @return ModelAndView with error message
     */
    @GetMapping("/accessDenied")
    public ModelAndView getCard(Authentication authentication) {
        model.addObject(MESSAGE_ERROR, ACCESS_DENIED_ERROR_MESS);
        return model;
    }

}
