package com.example.springdatademo;

import com.example.springdatademo.model.Product;
import com.example.springdatademo.repository.ProductRepository;
import com.example.springdatademo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class SpringDataDemoApplication {

    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringDataDemoApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        fillProducts();

        orderService.addOrder();
        orderService.addOrder();

        orderService.addProductToOrder(-45,"Orbit");
        orderService.addProductToOrder(-45,"Coca-cola");
        orderService.addProductToOrder(-45,"Cheese");

        orderService.addProductToOrder(-46,"Bread");
        orderService.addProductToOrder(-46,"Coca-cola");
        orderService.addProductToOrder(-46,"Bread");

        orderService.getOrders();
        orderService.getById(-45);

        orderService.deleteOrder(-45);

    }

    private void fillProducts() {
        Product product = new Product();

        product.setName("Orbit");
        product.setCost(14.5f);
        productRepository.save(product);

        product = new Product();
        product.setName("Cheese");
        product.setCost(40.5f);
        productRepository.save(product);

        product = new Product();
        product.setName("Bread");
        product.setCost(20.3f);
        productRepository.save(product);

        product = new Product();
        product.setName("Water");
        product.setCost(10.2f);
        productRepository.save(product);

        product = new Product();
        product.setName("Coca-cola");
        product.setCost(44.5f);
        productRepository.save(product);
    }
}
