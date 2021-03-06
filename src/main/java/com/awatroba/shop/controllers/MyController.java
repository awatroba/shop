package com.awatroba.shop.controllers;

import com.awatroba.shop.enums.Role;
import com.awatroba.shop.models.UserDetailsImp;
import org.springframework.security.core.Authentication;

/**
 * @author Angelika
 * class containing common elements for all controllers
 */
public class MyController {
    protected static String MESSAGE_ERROR = "messageError";
    protected static String LOGIN_MESSAGE_ERROR = "Your username or password are invalid.";
    protected static String LOGGED_OUT_SUCC_MESSAGE = "You have been logged out successfully.";
    protected static String MESSAGE = "message";
    protected static String CREATE_PRODUCT_REQUEST = "productRequest";
    protected static String MESSAGE_SUCCESS = "messageSuccess";
    protected static String PRODUCTS_PARAM = "products";
    protected static String DELL_MESS = "Product has been removed";
    protected static String ADD_MESS = "Product has been added";
    protected static String IS_ADMIN = "isAdmin";
    protected static String CREATE_USER_REQUEST = "userRequest";
    protected static String PRODUCT_PARAM = "product";
    protected static String TOTAL_PARAM = "total";
    protected static String ACCESS_DENIED_ERROR_MESS = "You do not have sufficient permissions for these resources";
    protected static String BUY_SUCCESS_MESS = "Products have been purchased, thank you for shopping";
    protected static String DEL_SUCCESS_MESS = "Products have been removed";
    protected static String SHOPPING_CART_EMPTY = "Shopping cart is empty!";


    protected static String SHOPPING_CART_MODEL_NAME = "cart";
    protected static String ACCESS_DENIED_CART_MODEL_NAME = "accessDenied";
    protected static String REGISTER_MODEL_NAME = "register";
    protected static String LOGIN_MODEL_NAME = "login";
    protected static String DASHBOARD_MODEL_NAME = "dashboard";
    protected static String PROD_DET_MODEL_NAME = "showProduct";
    protected static String ADMIN_PANEL_MODEL_NAME = "admin_panel";
    protected static String ERROR_MODEL_NAME = "error";


    /**
     * function get tru if user is admin
     *
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    protected boolean idAdmin(Authentication authentication) {
        Role role = ((UserDetailsImp) authentication.getPrincipal()).getUserRole();
        return role.equals(Role.ADMIN) ? true : false;
    }

}
