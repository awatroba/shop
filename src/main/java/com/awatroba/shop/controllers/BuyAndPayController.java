package com.awatroba.shop.controllers;

import com.awatroba.shop.helpers.BuyRequest;
import com.awatroba.shop.helpers.PayRequest;
import com.awatroba.shop.services.BuyAndPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import pay.PayStrategy;

/**
 * @author Angelika
 * Buy and pay controller
 */

@Controller
public class BuyAndPayController extends MyController {
    private ModelAndView model;
    private BuyAndPayService service;

    @Autowired
    public BuyAndPayController(BuyAndPayService service) {
        this.service = service;
    }

    /**
     * function for buy and pay
     *
     * @return ModelAndView with message and attribute
     */
    @PostMapping("/buy")
    public ModelAndView buy(@ModelAttribute BuyRequest request) {
        model = new ModelAndView(service.getViewName(request));
        model = service.getModelAndView(request);
        return model;
    }
    /**
     * function for buy and pay
     *
     * @return ModelAndView with message and attribute
     */
    @PostMapping("/pay")
    public ModelAndView pay(@ModelAttribute PayRequest request) {
       PayStrategy payStrategy= (PayStrategy) model.getModel().get(PayStrategy.PAY_STRATEGY);
        if(payStrategy.verify(request) && payStrategy.pay(request) ){
            model.addObject(MyController.MESSAGE_SUCCESS,MyController.PAY_SUCCESS);
        }else{
            model.addObject(MyController.MESSAGE_ERROR,MyController.PAY_ERROR);
        }
        return model;
    }


}
