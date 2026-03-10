package com.ecommerce.ecommerce_backend.repository;


import com.ecommerce.ecommerce_backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
