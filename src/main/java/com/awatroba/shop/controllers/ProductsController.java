package com.awatroba.shop.controllers;

import com.awatroba.shop.enums.CategoryProduct;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.helpers.CreateUserRequest;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController {
    private ModelAndView model;
    private ProductsService productsService;

    private static String CREATE_USER_REQUEST = "userRequest";
    private static String MESSAGE_ERROR = "messageError";
    private static String MESSAGE_SUCCESS = "messageSuccess";
    private static String PRODUCTS_PARAM = "products";
    private static String PRODUCT_PARAM = "product";
    private static String DASHBOARD_MODEL_NAME = "dashboard";
    private static String PROD_DET_MODEL_NAME = "showProduct";

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
        model = new ModelAndView(DASHBOARD_MODEL_NAME);
        model.addObject(CREATE_USER_REQUEST, new CreateUserRequest());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
    }
    /**
     * function get dashboard for user
     *
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/dashboard")
    public ModelAndView getDashboard() {
        model.setViewName(DASHBOARD_MODEL_NAME);
        model.addObject(PRODUCTS_PARAM, productsService.getAllProducts());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        return model;
    }
    /**
     * function get product details by id
     * @param id             product id
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/dashboard/{id}")
    public ModelAndView getProductById(@PathVariable("id") Long id) {
        model.setViewName(PROD_DET_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        try {
            model.addObject(PRODUCT_PARAM, productsService.getProductById(id));
        }catch (ProductNotFoundException e){
            model.addObject(MESSAGE_ERROR, e.getMessage());
            model.addObject(PRODUCT_PARAM, new Product());
        }
        return model;
    }
    /**
     * function get product details by id
     * @param category             product category
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/category/{category}")
    public ModelAndView getProductByCategory(@PathVariable("category") String category) {
        model.setViewName(DASHBOARD_MODEL_NAME);
        model.addObject(PRODUCTS_PARAM, productsService.getAllProductsByCategory(category));
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        return model;
    }

}
