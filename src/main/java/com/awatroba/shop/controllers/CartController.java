package com.awatroba.shop.controllers;

import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.helpers.BuyRequest;
import com.awatroba.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Shopping cart controller
 */
@Controller
public class CartController extends MyController {
    private ModelAndView model;
    private CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
        model = new ModelAndView(SHOPPING_CART_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, false);
        model.addObject(TOTAL_PARAM, 0);
        model.addObject(BUY_BUTTON_CLICK, false);
    }

    /**
     * function get shopping cart for user
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/cart")
    public ModelAndView getCard(Authentication authentication) {
        model.addObject(PAY_CLICKED,false);
        model.addObject(DELIVERY_AND_PAY_CLICKED,false);

        getBasicViewModel(authentication);
        return model;
    }

    /**
     * function to adding product to shopping cart
     *
     * @param id             product id
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    @PostMapping("/cart/{id}")
    public ModelAndView addProductToCard(
            @PathVariable("id") Long id, Authentication authentication) {
        model.addObject(PAY_CLICKED,false);
        model.addObject(DELIVERY_AND_PAY_CLICKED,false);

        cartService.addProductToCart(authentication, id);
        getBasicViewModel(authentication);
        return model;
    }

    /**
     * function to deleting product from shopping cart
     *
     * @param id             product id
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    @DeleteMapping("/cart/{id}")
    public ModelAndView deleteProductById(@PathVariable("id") Long id, Authentication authentication) {
        model.addObject(PAY_CLICKED,false);
        model.addObject(DELIVERY_AND_PAY_CLICKED,false);

        cartService.deleteProduct(authentication, id);
        getBasicViewModel(authentication);
        return model;
    }

    /**
     * helpers function to get basic modelAndView
     *
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    public ModelAndView getBasicViewModel(Authentication authentication) {
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, isAdmin(authentication));
        model.addObject(BUY_BUTTON_CLICK, false);
        model.addObject(ORDER, null);
        model.addObject(BUY_REQUEST, new BuyRequest(getUserId(authentication)));
        try {
            model.addObject(PRODUCTS_PARAM,
                    cartService.getUsersShoppingCart(authentication).getProducts());
            model.addObject(TOTAL_PARAM, cartService.getTotalInCart(authentication));
        } catch (ShoppingCartNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
        }
        return model;
    }

    /**
     * function for make order
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/order")
    public ModelAndView makeOrder(Authentication authentication) {
        if (cartService.getUsersShoppingCart(authentication).getProducts().size() > 0) {
            getBasicViewModel(authentication);
            model.addObject(ORDER, cartService.makeOrder(authentication));
            model.addObject(PAY_CLICKED,false);
            model.addObject(DELIVERY_AND_PAY_CLICKED,true);
        } else {
            getBasicViewModel(authentication);
            model.addObject(MESSAGE_ERROR, SHOPPING_CART_EMPTY);
        }
        return model;
    }

}
