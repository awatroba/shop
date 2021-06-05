package com.awatroba.shop.controllers;

import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController {
    private ModelAndView model;
    private CartService cartService;

    private static String MESSAGE_ERROR = "messageError";
    private static String MESSAGE_SUCCESS = "messageSuccess";
    private static String PRODUCTS_PARAM = "products";
    private static String SHOPPING_CART_PARAM = "shoppingCart";
    private static String MODEL_NAME = "cart";

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;

        model = new ModelAndView(MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
    }

    @GetMapping("/cart")
    public ModelAndView getCard(Authentication authentication) {
        getBasicViewModel(authentication);
        return model;
    }


    public ModelAndView getBasicViewModel(Authentication authentication) {
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        try {
            model.addObject(SHOPPING_CART_PARAM,
                    cartService.getUsersShoppingCart(authentication));
        } catch (ShoppingCartNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
        }
        return model;
    }


}
