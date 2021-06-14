package com.awatroba.shop.services;

import com.awatroba.shop.helpers.BuyRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Service that processes pay and buy
 */
@Service
public class BuyAndPayService {

    //TODO exception handler
    public String getViewName(BuyRequest request) {
        return request.getPayMethod().getPayStrategy().getViewName();
    }

    //TODO exception handler
    public ModelAndView getModelAndView(BuyRequest request) {
        return request.getPayMethod().getPayStrategy().getModelAndView();
    }
}
