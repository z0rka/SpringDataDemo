package com.example.springdatademo.service;

import com.example.springdatademo.dto.OrderDto;
import com.example.springdatademo.dto.OrderProductDto;
import com.example.springdatademo.dto.ProductDto;
import com.example.springdatademo.dto.ProductDtoList;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * Rest  service for program
 */
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
    public List<ProductDto> getById(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);


        List<OrderProduct> orderProducts = order
                .orElseThrow(() ->
                        new EntityNotFoundException("Order with id " + id + "wasn`t found"))
                .getOrderProducts();

        List<ProductDto> productDtoList = orderProducts
                .stream()
                .map(orderProduct -> objectMapper.convertValue(orderProduct.getProduct(), ProductDto.class))
                .collect(Collectors.toList());

        log.info("Order with id " + id + " was found");
        productDtoList.forEach(productDto -> log.info("{} \t {} \t {}", productDto.getId(), productDto.getName(), productDto.getCost()));

        return productDtoList;
    }

    /**
     * Showing all orders
     *
     * @return list {@link OrderProductDto}
     */
    @GetMapping("/get_all")
    public Map<OrderDto, ProductDtoList> getOrders() {
        Map<OrderDto, ProductDtoList> orderProducts = new HashMap<>();
        Iterable<OrderProduct> allOrders = orderProductRepository.findAll();

        allOrders.forEach(orderProduct -> {
            OrderDto orderDto = objectMapper.convertValue(orderProduct.getOrder(), OrderDto.class);
            ProductDto productDto = objectMapper.convertValue(orderProduct.getProduct(), ProductDto.class);

            orderProducts.putIfAbsent(orderDto, new ProductDtoList());
            orderProducts.get(orderDto).getProductDtoList().add(productDto);
        });

        log.info("Orders from database");
        Set<Map.Entry<OrderDto, ProductDtoList>> entries = orderProducts.entrySet();
        entries.forEach(entry -> {
            log.info(entry.getKey().toString());

            ProductDtoList value = entry.getValue();
            value
                    .getProductDtoList()
                    .forEach(productDto ->
                            log.info("{} \t {} \t {}", productDto.getId(), productDto.getName(), productDto.getCost()));
        });
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

        log.info("Product " + productName + " was added to the order number " + id);
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

        log.info("order with id " + id + " was deleted");

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

        log.info("New order was placed");
    }
}
