package com.example.springdatademo.repository;


import com.example.springdatademo.model.Order;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Modifying
    @Query("update Order u set u.cost = ?2 where u.id = ?1")
    void updateCost(@Param("id") int id,@Param("cost") float cost);
}
