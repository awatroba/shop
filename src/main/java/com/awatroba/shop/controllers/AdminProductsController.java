package com.awatroba.shop.controllers;

import com.awatroba.shop.helpers.CreateProductRequest;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Angelika
 * Admin dashboard request controller
 */

@Controller()
public class AdminProductsController {
    private ModelAndView model;
    private ProductsService productsService;
    private static String CREATE_PRODUCT_REQUEST = "productRequest";
    private static String MESSAGE_ERROR = "messageError";
    private static String MESSAGE_SUCCESS = "messageSuccess";
    private static String PRODUCTS_PARAM = "products";
    private static String DELL_MESS = "Product has been removed";
    private static String ADD_MESS = "Product has been added";
    private static String MODEL_NAME = "admin_panel";
    private static String IS_ADMIN = "isAdmin";

    @Autowired
    public AdminProductsController(ProductsService productsService) {
        this.productsService = productsService;
        model = new ModelAndView(MODEL_NAME);
        model.addObject(CREATE_PRODUCT_REQUEST, new CreateProductRequest());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, true);

    }

    /**
     * function get dashboard for user with ADMIN role
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/admin")
    public ModelAndView getDashboard() {
        model.addObject(PRODUCTS_PARAM, (List<Product>) productsService.getAllProducts());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        return model;
    }

    /**
     * function to adding product to database
     * @param request create product request
     * @return ModelAndView with message and attribute
     */
    @PostMapping("/admin")
    public ModelAndView addProduct(@ModelAttribute CreateProductRequest request) {
        String mess = productsService.addNewProduct(request);
        if (mess.equals("")) {
            model.addObject(MESSAGE_SUCCESS, ADD_MESS);
            model.addObject(MESSAGE_ERROR, "");
        } else {
            model.addObject(MESSAGE_SUCCESS, "");
            model.addObject(MESSAGE_ERROR, mess);
        }
        model.addObject(PRODUCTS_PARAM, productsService.getAllProducts());
        return model;
    }

    /**
     * function to deleting product from database
     * @param id product id
     * @return ModelAndView with message and attribute
     */
    @DeleteMapping("/admin/{id}")
    public ModelAndView deleteProductById(@PathVariable("id") Long id) {
        productsService.deleteProduct(id);
        model.addObject(PRODUCTS_PARAM, productsService.getAllProducts());
        model.addObject(MESSAGE_SUCCESS, DELL_MESS);
        return model;
    }
}
