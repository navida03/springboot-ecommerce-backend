package com.ecommerce.ecommerce_backend.controller;


import com.ecommerce.ecommerce_backend.model.Cart;
import com.ecommerce.ecommerce_backend.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestBody Cart cart) {
        return cartService.addToCart(cart);
    }

    @GetMapping
    public List<Cart> getCartItems() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/remove/{id}")
    public String removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
        return "Item removed from cart";
    }
}