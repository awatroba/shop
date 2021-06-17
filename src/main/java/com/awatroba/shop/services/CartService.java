package com.awatroba.shop.services;

import com.awatroba.shop.database.CartRepo;
import com.awatroba.shop.database.OrderRepo;
import com.awatroba.shop.database.ProductRepo;
import com.awatroba.shop.database.UserRepo;
import com.awatroba.shop.exception.ProductNotFoundException;
import com.awatroba.shop.exception.ShoppingCartNotFoundException;
import com.awatroba.shop.helpers.BuyRequest;
import com.awatroba.shop.models.Product;
import com.awatroba.shop.models.ShoppingCart;
import com.awatroba.shop.models.UserDetailsImp;
import com.awatroba.shop.models.UserOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

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

    private UserOrder order;

    @Autowired
    public CartService(CartRepo cartRepo, UserRepo userRepo, ProductRepo productRepo, OrderRepo orderRepo) {
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
     * @param productId
     * @return deleted product
     */
    public Product deleteProduct(Long productId, Authentication authentication) {
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

    //TODO: add exception
    public UserOrder makeOrder(Authentication authentication) {
        ShoppingCart shoppingCart = getUsersShoppingCart(authentication);

        order = new UserOrder(shoppingCart.getUser());
        orderRepo.save(order);

        order.setProducts(shoppingCart.getProducts());
        order.setClosed(false);
        order.setTotalCost(getTotalInCart(authentication));
        return order;
    }

    //TODO exception handler
    public String getViewName(BuyRequest request) {
        return request.getPayMethod().getPayStrategy().getViewName();
    }

    //TODO exception handler
    public ModelAndView getModelAndView(BuyRequest request, ModelAndView modelAndView) {
        return request.getPayMethod().getPayStrategy().getModelAndView(modelAndView);
    }

    //TODO: add exception
    public UserOrder addOrderDetails(BuyRequest request) {
        order.setDeliveryMethod(request.getDeliveryMethod());
        order.setPayMethod(request.getPayMethod());
        order.setClosed(false);

        order.getProducts().stream().forEach(p -> p.setUserOrder(order));
        productRepo.saveAll(order.getProducts());

        orderRepo.save(order);
        return order;
    }

    //TODO: add exception
    public void closeAndSaveOrder(Authentication authentication) {
        order.setClosed(true);
        orderRepo.save(order);

        ShoppingCart shoppingCart = getUsersShoppingCart(authentication);
        shoppingCart.getProducts().clear();
        cartRepo.save(shoppingCart);
    }
}
