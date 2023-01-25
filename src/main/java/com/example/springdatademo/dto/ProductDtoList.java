package com.example.springdatademo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * List of the {@link ProductDto}
 */
@Data
public class ProductDtoList {
    private final List<ProductDto> productDtoList = new ArrayList<>();
}
