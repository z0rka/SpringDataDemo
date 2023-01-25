package com.example.springdatademo.repository;

import com.example.springdatademo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository of the {@link Product}
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /**
     * Search by name method
     *
     * @param name - product name
     */
    Optional<Product> findByName(String name);
}
