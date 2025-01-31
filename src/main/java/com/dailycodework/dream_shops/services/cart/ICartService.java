package com.dailycodework.dream_shops.services.cart;

import com.dailycodework.dream_shops.models.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalAmount(Long cartId);

    Long initializeNewCart();
    // List<CartItem> getCartItems(Long cartId);
}
