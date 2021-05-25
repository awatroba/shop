package com.awatroba.shop.controllers;

import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.helpers.CreateProductRequest;
import com.awatroba.shop.helpers.Response;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * @author Angelika
 * Dashboard request controller
 */

@RestController()
@RequestMapping("/dashboard")
public class ProductsController {
    @Autowired
    ProductsService productsService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object>  getAllProducts() {
        Response<Iterable<Product>> response =
                new Response<Iterable<Product>>("success",  productsService.getAllProducts());
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }
    @RequestMapping(value = "/{category}",method = RequestMethod.GET)
    public ResponseEntity<Object> getAllProductsByCategory(@PathVariable String category) {
        Response<Iterable<Product>> response =
                new Response<Iterable<Product>>("success",  productsService.getAllProductsByCategory(category));
        return new ResponseEntity<Object>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET)
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        Response<Product> response;
        try{
            response =  new Response<Product>("success",  productsService.getProductById(id));
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            response =  new Response<Product>("error",  new Product());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> addNewProduct(@RequestBody CreateProductRequest createProductRequest) {
        String errorMessage =productsService.addNewProduct(createProductRequest);
        if(errorMessage.equals("")){
            Response<CreateProductRequest>  response =
                    new Response<CreateProductRequest>("success", createProductRequest);
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        } else {
            Response<String> response =
                    new Response<String>("error", errorMessage);
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteBook(@PathVariable Long productId) {
        Response<Product> response;
        try{
            response =  new Response<Product>("success",  productsService.deleteProduct(productId));
            return new ResponseEntity<Object>(response, HttpStatus.OK);
        }catch (ProductNotFoundException e){
            response =  new Response<Product>("error",  new Product());
            return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
