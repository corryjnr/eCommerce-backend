package com.dailycodework.dream_shops.dto;

import com.dailycodework.dream_shops.models.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Data
public class CartDto {
    // private Long id;
    private BigDecimal totalAmount;
    private List<CartItemDto> cartItems;

}
