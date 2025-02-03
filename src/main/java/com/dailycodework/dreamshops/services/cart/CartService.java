package com.dailycodework.dreamshops.services.cart;

import com.dailycodework.dreamshops.dto.*;
import com.dailycodework.dreamshops.exception.ResourceNotFoundException;
import com.dailycodework.dreamshops.models.*;
import com.dailycodework.dreamshops.repositories.CartItemRepository;
import com.dailycodework.dreamshops.repositories.CartRepository;
import com.dailycodework.dreamshops.services.product.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService implements ICartService{
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ModelMapper modelMapper;
    private final IProductService productService;

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found!"));
    }

    @Transactional
    @Override
    public void clearCart(Long cartId) {
        Cart cart = getCart(cartId);
        cartItemRepository.deleteAllByCartId(cartId);
        cart.getCartItems().clear();
        cartRepository.deleteById(cartId);
    }

    @Override
    public BigDecimal getTotalAmount(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart(){
        Cart newCart = new Cart();
        // Long newCartId = cartIdGenerator.incrementAndGet();
        // newCart.setId(newCartId);
        return cartRepository.save(newCart).getId();
    }

    @Override
    public List<CartDto> getConvertedCarts(List<Cart> carts){
        return carts.stream().map(this::convertToDto).toList();
    }

    @Override
    public CartDto convertToDto(Cart cart){
        List<CartItem> cartItems = cart.getCartItems().stream().toList();
        return modelMapper.map(cart, CartDto.class);
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
