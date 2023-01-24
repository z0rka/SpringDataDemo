package com.example.springdatademo.repository;

import com.example.springdatademo.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface OrderProductRepository extends CrudRepository<OrderProduct,Integer> {
}
