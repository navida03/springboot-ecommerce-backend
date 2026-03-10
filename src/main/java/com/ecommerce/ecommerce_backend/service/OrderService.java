package com.ecommerce.ecommerce_backend.service;


import com.ecommerce.ecommerce_backend.model.Order;
import com.ecommerce.ecommerce_backend.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }
}
