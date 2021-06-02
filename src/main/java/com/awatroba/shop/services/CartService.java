package com.awatroba.shop.services;

import com.awatroba.shop.database.CartRepo;
import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.models.ShoppingCart;
import com.awatroba.shop.models.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Angelika
 * Service that processes shopping cart data
 */
@Service
public class CartService {
    private CartRepo cartRepo;
    private ProductRepo productRepo;

    @Autowired
    public CartService(CartRepo cartRepo,ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }

    public ShoppingCart getUsersShoppingCart(Authentication authentication){
        Long userId=getUserId(authentication);
        return cartRepo.findById(userId).orElseThrow(()->new ShoppingCartNotFoundException());
    }

    public Set<Product> getUsersProductInShoppingCart(Authentication authentication){
        ShoppingCart cart = getUsersShoppingCart(authentication);
        return cart.getProducts();
    }

    private Long getUserId(Authentication authentication) {
        return  ((UserDetailsImp)authentication.getPrincipal()).getUserId();
    }

    public void addProductToCart(Authentication authentication, Long id) {
        Product product = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException());
        ShoppingCart cart=getUsersShoppingCart(authentication);
        cart.addProduct(product);
        cartRepo.save(cart);
    }
}