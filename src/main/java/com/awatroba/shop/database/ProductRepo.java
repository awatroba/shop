package com.awatroba.shop.database;

import com.awatroba.shop.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Angelika
 * Repository for PRODUCT objects. Interface to manipulate Product data, write and read data
 **/
@Repository
public interface ProductRepo extends CrudRepository<Product, Long> {
    @Override
    Iterable<Product> findAll();

    @Override
    Optional<Product> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(Product product);

    Iterable<Product> findAllByEnable(boolean enable);

    Iterable<Product> findAllByCategoryAndEnable(String category, boolean enable);

}
