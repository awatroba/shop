package com.awatroba.shop.controllers;

import com.awatroba.shop.enums.Role;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.models.UserDetailsImp;
import com.awatroba.shop.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CartController extends MyController {
    private ModelAndView model;
    private CartService cartService;


    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
        model = new ModelAndView(MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, false);
    }

    /**
     * function get shopping cart for user
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/cart")
    public ModelAndView getCard(Authentication authentication) {
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
        model.addObject(IS_ADMIN, idAdmin(authentication));

        try {
            model.addObject(PRODUCTS_PARAM,
                    cartService.getUsersShoppingCart(authentication).getProducts());
        } catch (ShoppingCartNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
        }
        return model;
    }



}
