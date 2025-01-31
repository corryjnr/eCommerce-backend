package com.dailycodework.dream_shops.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemDto {
    // private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private ProductDto product;
}
