package com.awatroba.shop.services;

import com.awatroba.shop.database.CartRepo;
import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.models.Product;
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
    private ProductRepo productRepo;

    private ShoppingCart shoppingCart;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo,ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
    }

    public ShoppingCart getUsersShoppingCart(Authentication authentication) {
        Long userId = getUserId(authentication);
        return Optional.of(
                userRepo.findFirstById(userId).getShoppingCart())
                .orElseThrow(() -> new ShoppingCartNotFoundException());
    }
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    private Long getUserId(Authentication authentication) {
        return ((UserDetailsImp) authentication.getPrincipal()).getUserId();
    }

    public void addProductToCart(Authentication authentication, Long productId) {
        ShoppingCart shoppingCart = getUsersShoppingCart(authentication);

        Product product = getProductById(productId);
        product.setCart(shoppingCart);
        product.setEnable(false);
        productRepo.save(product);

        shoppingCart.addProduct(product);
        cartRepo.save(shoppingCart);
    }
}
