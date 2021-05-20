package com.awatroba.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 *
 * @author Angelika
 */
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path",
                "/");
        SpringApplication.run(ShopApplication.class, args);
    }

}
