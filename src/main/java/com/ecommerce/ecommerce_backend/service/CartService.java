package com.ecommerce.ecommerce_backend.service;


import com.ecommerce.ecommerce_backend.model.Cart;
import com.ecommerce.ecommerce_backend.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Add product to cart
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Get all cart items
    public List<Cart> getCartItems() {
        return cartRepository.findAll();
    }

    // Get cart items by user
    public List<Cart> getCartByUser(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    // Remove item from cart
    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
