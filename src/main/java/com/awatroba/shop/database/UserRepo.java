package com.awatroba.shop.database;

import com.awatroba.shop.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *  @author Angelika
 *  Repository for USER objects. Interface to manipulate User data, write and read data
 **/
@Repository
public interface UserRepo  extends CrudRepository<User,Long> {
    User findFirstById(Long id);
    User findFirstByLogin(String login);
}
