package com.example.springdatademo.service;

import com.example.springdatademo.dto.OrderDto;
import com.example.springdatademo.dto.OrderProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig
@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private static OrderService orderService;
    private static final int orderId = -45;

    @Test
    public void getByIdTest() {

        OrderProductDto order = orderService.getById(orderId);

        Assertions.assertNotNull(order);
        Assertions.assertInstanceOf(OrderProductDto.class, order);
        Assertions.assertEquals(orderId, order.getId());
    }

    @Test
    public void addProductToOrderTest() {

    }

    @Test
    public void deleteOrderTest() {
        OrderDto orderDto = orderService.deleteOrder(orderId);

        Assertions.assertNotNull(orderDto);
        Assertions.assertInstanceOf(OrderDto.class, orderDto);
        Assertions.assertEquals(orderId, orderDto.getId());
    }

    @Test
    public void getOrdersTest() {
        List<OrderProductDto> orders = orderService.getOrders();

        Assertions.assertNotNull(orders);
        Assertions.assertInstanceOf(OrderProductDto.class, orders.get(0));
    }
}
