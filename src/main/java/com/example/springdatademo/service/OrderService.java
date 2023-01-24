package com.example.springdatademo.service;

import com.example.springdatademo.dto.OrderDto;
import com.example.springdatademo.dto.OrderProductDto;
import com.example.springdatademo.dto.ProductDto;
import com.example.springdatademo.model.Order;
import com.example.springdatademo.model.OrderProduct;
import com.example.springdatademo.model.Product;
import com.example.springdatademo.repository.OrderProductRepository;
import com.example.springdatademo.repository.OrderRepository;
import com.example.springdatademo.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RestController
@RequestMapping("order")
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;

    private final ObjectMapper objectMapper;

    /**
     * Showing order with exact id
     *
     * @param id - order id
     * @return {@link OrderProductDto}
     */
    @GetMapping("{id}")
    public OrderDto getById(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);

     
        log.info("Order from database :" );
        return null;
    }

    /**
     * Showing all orders
     *
     * @return list {@link OrderProductDto}
     */
    @GetMapping("/get_all")
    public List<OrderProductDto> getOrders() {
        List<OrderProductDto> orderProducts = new ArrayList<>();
        Iterable<OrderProduct> allOrders = orderProductRepository.findAll();


        allOrders.forEach(orderProduct -> {
            OrderProductDto dto = objectMapper.convertValue(orderProduct, OrderProductDto.class);
            orderProducts.add(dto);
        });

        log.info("Orders from database :");
        orderProducts.forEach(product -> log.info("{}  {} " + product.getOrder() + product.getProduct()));
        return orderProducts;

    }

    /**
     * Adding product to the order
     *
     * @param id          - order id
     * @param productName - name of the product
     */
    @PutMapping("{id}")
    @Transactional
    public void addProductToOrder(@PathVariable int id, @RequestParam String productName) {
        OrderProduct orderProduct = new OrderProduct();
        Optional<Order> order = orderRepository.findById(id);
        Optional<Product> product = productRepository.findByName(productName);

        orderProduct.setProduct(product.orElseThrow(() ->
                new EntityNotFoundException("Product with name " + productName + "wasn`t found")));

        orderProduct.setOrder(order.orElseThrow(() ->
                new EntityNotFoundException("Order with id " + id + "wasn`t found")));

        orderProductRepository.save(orderProduct);

        float cost = order.get().getCost() + product.get().getCost();

        orderRepository.updateCost(order.get().getId(), cost);
    }

    /**
     * Deleting order
     *
     * @param id - order id
     * @return {@link Order}
     */
    @DeleteMapping("{id}")
    public OrderDto deleteOrder(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);
        order.orElseThrow(() -> new EntityNotFoundException("Order with id " + id + "wasn`t found"));

        orderRepository.deleteById(id);
        return objectMapper.convertValue(order, OrderDto.class);
    }

    /**
     * Just creating new order
     */
    @PostMapping("")
    public void addOrder() {
        Order order = new Order();
        order.setCost(0.0f);
        order.setCreationDate(LocalDate.now());
        orderRepository.save(order);
    }
}
