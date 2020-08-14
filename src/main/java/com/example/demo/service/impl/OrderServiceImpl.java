package com.example.demo.service.impl;

import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;

    @Autowired
    public OrderServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Order createOrder(Order order) {
        order.getProducts().stream()
                .map(Product::getPrice)
                .reduce(Double::sum)
                .ifPresent(order::setTotalPrice);
        return ordersRepository.save(order);
    }
}
