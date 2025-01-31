package com.dailycodework.dream_shops.services.cart;

import com.dailycodework.dream_shops.dto.CartDto;
import com.dailycodework.dream_shops.models.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalAmount(Long cartId);

    Long initializeNewCart();

    List<CartDto> getConvertedCarts(List<Cart> carts);

    CartDto convertToDto(Cart cart);
    // List<CartItem> getCartItems(Long cartId);
}
