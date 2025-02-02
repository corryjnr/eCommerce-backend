package com.dailycodework.dreamshops.services.cart;

import com.dailycodework.dreamshops.dto.CartDto;
import com.dailycodework.dreamshops.models.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface ICartService {
    Cart getCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalAmount(Long cartId);

    Long initializeNewCart();

    List<CartDto> getConvertedCarts(List<Cart> carts);

    CartDto convertToDto(Cart cart);

    Cart getCartByUserId(Long userId);
    // List<CartItem> getCartItems(Long cartId);
}
