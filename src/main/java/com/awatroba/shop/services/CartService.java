package com.awatroba.shop.services;

import com.awatroba.shop.database.CartRepo;
import com.awatroba.shop.database.OrderRepo;
import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.models.ShoppingCart;
import com.awatroba.shop.models.UserDetailsImp;
import com.awatroba.shop.models.UserOrder;
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
    private OrderRepo orderRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo, ProductRepo productRepo,OrderRepo orderRepo) {
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    /**
     * function getting product by id
     *
     * @param authentication authentication for getting ser id
     * @return ShoppingCart for logged user
     */
    public ShoppingCart getUsersShoppingCart(Authentication authentication) {
        Long userId = getUserId(authentication);
        return Optional.of(
                userRepo.findFirstById(userId).getShoppingCart())
                .orElseThrow(() -> new ShoppingCartNotFoundException());
    }

    /**
     * function getting product by id
     *
     * @param id product id
     * @return product
     */
    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new ProductNotFoundException());
    }

    /**
     * function delete product from Cart
     *
     * @param authentication authentication for getting ser id
     * @return logged user id
     */
    private Long getUserId(Authentication authentication) {
        return ((UserDetailsImp) authentication.getPrincipal()).getUserId();
    }

    /**
     * function to adding product from Cart
     *
     * @param authentication authentication for getting ser id
     * @param productId
     */
    public void addProductToCart(Authentication authentication, Long productId) {
        ShoppingCart shoppingCart = getUsersShoppingCart(authentication);

        Product product = getProductById(productId);
        product.setCart(shoppingCart);
        product.setEnable(false);
        productRepo.save(product);

        shoppingCart.addProduct(product);
        cartRepo.save(shoppingCart);
    }

    /**
     * function delete product from Cart
     *
     * @param authentication authentication for getting ser id
     * @param productId
     * @return deleted product
     */
    public Product deleteProduct(Authentication authentication, Long productId) {
        Product prodToDelete =
                Optional.of(getProductById(productId)).orElseThrow(
                        () -> new ProductNotFoundException());

        prodToDelete.setEnable(true);
        prodToDelete.setCart(null);
        productRepo.save(prodToDelete);

        ShoppingCart shoppingCart = getUsersShoppingCart(authentication);
        shoppingCart.deleteProduct(prodToDelete);
        cartRepo.save(shoppingCart);

        return prodToDelete;
    }

    /**
     * helpers function to get total in shopping cart
     *
     * @param authentication authentication for getting ser id
     * @return total
     */
    public double getTotalInCart(Authentication authentication) {
        return getUsersShoppingCart(authentication).
                getProducts().stream()
                .mapToDouble(
                        p -> p.getPrice())
                .sum();
    }

    public void deleteAllProducts(Authentication authentication) {
        ShoppingCart cart = getUsersShoppingCart(authentication);
        cart.getProducts().stream().forEach(p->p.setCart(null));

        productRepo.saveAll(cart.getProducts());
        cart.getProducts().clear();
        cartRepo.save(cart);
    }

    public UserOrder makeOrder(Authentication authentication) {
        //double total=getTotalInCart(authentication);
        //Set<Product> products = getUsersShoppingCart(authentication).getProducts();
       // return new Order(total,products);
        return new UserOrder();
    }


}
