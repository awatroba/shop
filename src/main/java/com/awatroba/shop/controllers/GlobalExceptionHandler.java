package com.awatroba.shop.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler extends MyController {
    
    @ExceptionHandler({NullPointerException.class, ArrayIndexOutOfBoundsException.class, IOException.class})
    public ModelAndView handleException(NullPointerException ex)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_MODEL_NAME);
        modelAndView.addObject(MESSAGE_ERROR, ex.getMessage());
        return modelAndView;
    }
    @ExceptionHandler({Exception.class})
    public ModelAndView handleException(Exception ex)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ERROR_MODEL_NAME);
        modelAndView.addObject(MESSAGE_ERROR, ex.getMessage());
        return modelAndView;
    }
}
