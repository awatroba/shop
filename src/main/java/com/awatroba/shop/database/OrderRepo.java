package com.awatroba.shop.database;

import com.awatroba.shop.models.UserOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Angelika
 * Repository for ORDER objects. Interface to manipulate shopping order data, write and read data
 **/
@Repository
public interface OrderRepo extends CrudRepository<UserOrder, Long> {
}
