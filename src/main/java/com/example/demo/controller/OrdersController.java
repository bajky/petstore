package com.example.demo.controller;

import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RepositoryRestController
public class OrdersController {

    private final OrderService orderService;

    @Autowired
    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/orders", consumes = MediaTypes.HAL_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<EntityModel<Order>> save(@RequestBody EntityModel<Order> order) {
        EntityModel.of(order);
        return ResponseEntity.ok(order);
    }
}
