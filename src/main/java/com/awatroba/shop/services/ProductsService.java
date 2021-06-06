package com.awatroba.shop.services;

import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.enums.CategoryProduct;
import com.awatroba.shop.exception.CategoryNotFoundException;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.helpers.CreateProductRequest;
import com.awatroba.shop.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Angelika
 * Service that processes product data
 */
@Service
public class ProductsService {
    private static String ADDING_ERROR = "Adding error - please complete all fields!";
    private static String ADDING_ERROR_PRICE = "Adding error -price must be greater than 0!";
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
        return productRepo.findAllByEnable(true);
    }

    /**
     * function adding product
     *
     * @param category
     * @return all products by category
     */
    public Iterable<Product> getAllProductsByCategory(String category) {
        CategoryProduct categoryProduct =
                CategoryProduct.stream().filter(c ->
                        c.getDisplayName().equals(category)).findFirst().
                        orElseThrow(() -> new CategoryNotFoundException());
        return productRepo.findAllByCategoryAndEnable(categoryProduct, true);
    }

    /**
     * function adding product
     *
     * @param productId
     * @return product by id or throw exception
     */
    public Product getProductById(Long productId) {
        return productRepo.findById(productId).orElseThrow(
                () -> new ProductNotFoundException());
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
        productRepo.save(new Product(request.getName(),
                round(request.getPrice(), 2), request.getDescription(),
                request.getCategory()));
        return "";
    }

    /**
     * function for data validation
     *
     * @param request create product request
     * @return errorMessage or ""
     */
    private String checkProductData(CreateProductRequest request) {
        if (request.getDescription().isEmpty() ||
                request.getName().isEmpty())
            return ADDING_ERROR;
        if (request.getPrice() <= 0)
            return ADDING_ERROR_PRICE;
        return "";
    }

    /**
     * function adding product
     *
     * @param productId
     * @return deleted product
     */
    public Product deleteProduct(Long productId) {
        Product prodToDelete =
                Optional.of(getProductById(productId)).orElseThrow(
                        () -> new ProductNotFoundException());
        productRepo.deleteById(productId);
        return prodToDelete;
    }

    /**
     * helpers function
     *
     * @param value  -value to round
     * @param places -decimal places
     * @return rounded number
     */
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
