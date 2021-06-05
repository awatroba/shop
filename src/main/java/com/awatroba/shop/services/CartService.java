package com.awatroba.shop.services;

import com.awatroba.shop.database.CartRepo;
import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.models.ShoppingCart;
import com.awatroba.shop.models.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Angelika
 * Service that processes shopping cart data
 */
@Service
public class CartService {
    private CartRepo cartRepo;
    private UserRepo userRepo;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    public ShoppingCart getUsersShoppingCart(Authentication authentication) {
        Long userId = getUserId(authentication);
        return Optional.of(
                userRepo.findFirstById(userId).getShoppingCart())
                .orElseThrow(() -> new ShoppingCartNotFoundException());
    }

    private Long getUserId(Authentication authentication) {
        return ((UserDetailsImp) authentication.getPrincipal()).getUserId();
    }

}
