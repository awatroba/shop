package com.awatroba.shop.services;

import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.helpers.CreateProductRequest;
import com.awatroba.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author Angelika
 * Service that processes product data
 */
@Service
public class ProductsService {
    private ProductRepo productRepo;

    @Autowired
    public ProductsService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    /**
     * function adding product
     *
     * @return all products
     */
    public Iterable<Product> getAllProducts() {
        return productRepo.findAll();
    }

    /**
     * function adding product
     *
     * @param category
     * @return all products by category
     */
    public Iterable<Product> getAllProductsByCategory(String category) {
        return productRepo.findAllByCategory(category);
    }

    /**
     * function adding product
     *
     * @param productId
     * @return product by id or throw exception
     */
    public Product getProductById(Long productId) {
        return productRepo.findById(productId).orElseThrow(() -> new ProductNotFoundException());
    }

    /**
     * function adding product
     *
     * @param request create product request
     * @return errorMessage or ""
     */
    public String addNewProduct(CreateProductRequest request) {
        String errorMessage = checkProductData(request);
        if (!errorMessage.equals(""))
            return errorMessage;
        productRepo.save(new Product(request.getName(), request.getPrice(), request.getDescription(), request.getCategory()));
        return "";
    }

    /**
     * function for data validation
     *
     * @param request create product request
     * @return errorMessage or ""
     */
    //TODO: mate this function
    private String checkProductData(CreateProductRequest request) {
        return "";
    }

    /**
     * function adding product
     *
     * @param productId
     * @return deleted product
     */
    public Product deleteProduct(Long productId) {
        Product prodToDelete = getProductById(productId);
        productRepo.deleteById(productId);
        return prodToDelete;
    }
}
