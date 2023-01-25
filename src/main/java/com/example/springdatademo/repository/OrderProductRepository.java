package com.example.springdatademo.repository;

import com.example.springdatademo.model.OrderProduct;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository of the {@link OrderProduct}
 */
public interface OrderProductRepository extends CrudRepository<OrderProduct,Integer> {
}
