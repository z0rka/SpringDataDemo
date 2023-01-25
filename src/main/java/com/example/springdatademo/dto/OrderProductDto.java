package com.example.springdatademo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class DTO for {@link com.example.springdatademo.model.OrderProduct}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductDto {
    private Integer id;
    private OrderDto order;
    private ProductDto product;
}
