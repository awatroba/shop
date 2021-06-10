package com.awatroba.shop.controllers;

import com.awatroba.shop.exception.CategoryNotFoundException;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.helpers.CreateUserRequest;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductsController extends MyController {
    private ModelAndView model;
    private ProductsService productsService;

    @Autowired
    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
        model = new ModelAndView(DASHBOARD_MODEL_NAME);
        model.addObject(CREATE_USER_REQUEST, new CreateUserRequest());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, false);
    }

    /**
     * function get dashboard for user
     *
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/dashboard")
    public ModelAndView getDashboard(Authentication authentication) {
        model.setViewName(DASHBOARD_MODEL_NAME);
        model.addObject(PRODUCTS_PARAM, productsService.getAllProducts());
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, isAdmin(authentication));
        return model;
    }

    /**
     * function get product details by id
     *
     * @param id             product id
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/dashboard/{id}")
    public ModelAndView getProductById(@PathVariable("id") Long id, Authentication authentication) {
        model.setViewName(PROD_DET_MODEL_NAME);
        model.addObject(MESSAGE_ERROR, "");
        model.addObject(IS_ADMIN, isAdmin(authentication));
        model.addObject(MESSAGE_SUCCESS, "");
        try {
            model.addObject(PRODUCT_PARAM, productsService.getProductById(id));
        } catch (ProductNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
            model.addObject(PRODUCT_PARAM, new Product());
        }
        return model;
    }

    /**
     * function get product details by id
     *
     * @param category       product category
     * @param authentication authentication for getting ser id
     * @return ModelAndView with message and attribute
     */
    @GetMapping("/category/{category}")
    public ModelAndView getProductByCategory(@PathVariable("category") String category, Authentication authentication) {
        model.setViewName(DASHBOARD_MODEL_NAME);
        model.addObject(MESSAGE_SUCCESS, "");
        model.addObject(IS_ADMIN, isAdmin(authentication));
        try {
            model.addObject(PRODUCTS_PARAM, productsService.getAllProductsByCategory(category));
        } catch (CategoryNotFoundException e) {
            model.addObject(MESSAGE_ERROR, e.getMessage());
            return model;
        }
        model.addObject(MESSAGE_ERROR, "");

        return model;
    }

}
