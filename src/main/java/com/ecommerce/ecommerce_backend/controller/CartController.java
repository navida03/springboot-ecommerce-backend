package com.ecommerce.ecommerce_backend.controller;

import com.ecommerce.ecommerce_backend.dto.CartRequest;
import com.ecommerce.ecommerce_backend.dto.CartResponse;
import com.ecommerce.ecommerce_backend.model.Cart;
import com.ecommerce.ecommerce_backend.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<CartResponse> addToCart(@RequestBody CartRequest request) {

        Cart cart = cartService.addToCart(request);

        CartResponse response = new CartResponse();
        response.setUserName(cart.getUser().getName());
        response.setProductName(cart.getProduct().getName());
        response.setQuantity(cart.getQuantity());

        return ResponseEntity.ok(response);   // ✅ returning DTO
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<Cart>> getMyCart() {
        return ResponseEntity.ok(cartService.getMyCart());
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
        return ResponseEntity.ok(cartService.removeFromCart(cartId));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        return ResponseEntity.ok(cartService.clearCart());
    }
}