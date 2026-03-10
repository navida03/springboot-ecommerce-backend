package com.ecommerce.ecommerce_backend.repository;

import com.ecommerce.ecommerce_backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    List<Cart> findByUserId(Long userId);

}
