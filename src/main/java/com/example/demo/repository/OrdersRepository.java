package com.example.demo.repository;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;


@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrdersRepository extends PagingAndSortingRepository<Order, Long> {
    @RestResource(path = "byUser", rel = "byUser")
    @PreAuthorize(" #user != null and principal.username.equals(#user.userName) ")
    Page<Order> findByUser(@RequestParam("user") User user, Pageable pageable);
}