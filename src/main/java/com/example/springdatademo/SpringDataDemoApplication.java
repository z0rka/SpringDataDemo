package com.example.springdatademo;

import com.example.springdatademo.dto.OrderProductDto;
import com.example.springdatademo.model.Product;
import com.example.springdatademo.repository.ProductRepository;
import com.example.springdatademo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.List;


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
      orderService.getById(-47);
        System.out.println();
//        fillProducts();
//
//        orderService.addOrder();
//        orderService.addOrder();
//
//        orderService.addProductToOrder(-47,"Orbit");
//        orderService.addProductToOrder(-47,"Coca-cola");
//        orderService.addProductToOrder(-47,"Cheese");
//
//        orderService.addProductToOrder(-46,"Bread");
//        orderService.addProductToOrder(-46,"Coca-cola");
//        orderService.addProductToOrder(-46,"Bread");

    }

//    private void fillProducts() {
//        Product product = new Product();
//
//        product.setName("Orbit");
//        product.setCost(14.5f);
//        productRepository.save(product);
//
//        product = new Product();
//        product.setName("Cheese");
//        product.setCost(40.5f);
//        productRepository.save(product);
//
//        product = new Product();
//        product.setName("Bread");
//        product.setCost(20.3f);
//        productRepository.save(product);
//
//        product = new Product();
//        product.setName("Water");
//        product.setCost(10.2f);
//        productRepository.save(product);
//
//        product = new Product();
//        product.setName("Coca-cola");
//        product.setCost(44.5f);
//        productRepository.save(product);
//    }
}
