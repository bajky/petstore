package com.example.demo.service.impl;

import com.example.demo.exception.OrderException;
import com.example.demo.model.Category;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private OrdersRepository ordersRepository;

    @Before
    public void setup() {
    }

    @Test
    public void productsNull_willThrowException() {
        Order order = new Order();
        order.setProducts(null);

        OrderException exception =
                assertThrows(OrderException.class,
                () -> orderService.createOrder(new Order()),
                        "The exception should be thrown");
        assertTrue(exception.getMessage().contains("at least one"));
    }

    @Test
    public void productsEmpty_willThrowException() {
        Order order = new Order();
        order.setProducts(Collections.emptyList());

        OrderException exception =
                assertThrows(OrderException.class,
                        () -> orderService.createOrder(new Order()),
                        "The exception should be thrown");
        assertTrue(exception.getMessage().contains("at least one"));
    }

    @Test
    public void productsNotEmpty_orderCreatedSuccessfully() {
        Order order = new Order();

        order.setTime(new Date());
        order.setProducts(Collections.singletonList(createProduct(0.0)));
        order.setUser(createUser());

        orderService.createOrder(order);
        Mockito.when(ordersRepository.save(order)).thenReturn(order);
        Mockito.verify(ordersRepository, Mockito.times(1)).save(order);
    }

    @Test
    public void productsNotEmpty_totalPriceIsCorrect() {
        Order order = new Order();

        Product productA = createProduct(2.0);
        Product productB = createProduct(3.0);
        Product productC = createProduct(4.0);

        order.setTime(new Date());
        order.setTime(new Date());
        order.setProducts(Arrays.asList(productA, productB, productC));
        order.setUser(createUser());

        Mockito.when(ordersRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.createOrder(order);
        Mockito.verify(ordersRepository, Mockito.times(1)).save(order);

        double totalPrice = productA.getPrice() + productB.getPrice() + productC.getPrice();
        assertEquals(totalPrice, savedOrder.getTotalPrice());
    }


    private User createUser() {
        User user = new User();
        user.setUserName("userName");
        user.setLastName("lastName");
        user.setFirstName("firstName");
        user.setEmail("valid@email.com");
        user.setPassword("password");

        return user;
    }

    private Product createProduct(double price) {
        Product product = new Product();
        product.setCategory(Category.CAT);
        product.setPrice(price);

        return product;
    }
}