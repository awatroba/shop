package com.awatroba.shop.controllers;

import com.awatroba.shop.exception.ShoppingCartNotFoundException;
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
        model = new ModelAndView(SHOPPING_CART_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, false);
        model.addObject(TOTAL_PARAM, 0);
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
     * function get shopping cart for user
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/buy")
    public ModelAndView buy(Authentication authentication) {
        if (cartService.getUsersShoppingCart(authentication).getProducts().size() > 0) {
            cartService.deleteAllProducts(authentication);
            getBasicViewModel(authentication);
            model.addObject(MESSAGE_SUCCESS, BUY_SUCCESS_MESS);
        } else {
            getBasicViewModel(authentication);
            model.addObject(MESSAGE_ERROR, SHOPPING_CART_EMPTY);
        }


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
            model.addObject(TOTAL_PARAM, cartService.getTotalInCart(authentication));
        } catch (ShoppingCartNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
        }
        return model;
    }
}
