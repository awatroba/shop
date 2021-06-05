package com.awatroba.shop.database;

import com.awatroba.shop.models.ShoppingCart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Angelika
 * Repository for CART objects. Interface to manipulate shopping cart data, write and read data
 **/
@Repository
public interface CartRepo extends CrudRepository<ShoppingCart, Long> {
    @Override
    Iterable<ShoppingCart> findAll();

    @Override
    Optional<ShoppingCart> findById(Long aLong);

    @Override
    void deleteById(Long aLong);

}
