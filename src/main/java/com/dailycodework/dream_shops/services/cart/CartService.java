package com.dailycodework.dream_shops.services.cart;

import com.dailycodework.dream_shops.dto.*;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.models.*;
import com.dailycodework.dream_shops.repositories.CartItemRepository;
import com.dailycodework.dream_shops.repositories.CartRepository;
import com.dailycodework.dream_shops.services.product.IProductService;
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
        List<CartItemDto> cartItemDtos = cartItems.stream()
                .map(item -> {
                    ProductDto productDto = productService.convertToDto(item.getProduct());
                    CartItemDto newItem = modelMapper.map(item, CartItemDto.class);
                    newItem.setProduct(productDto);
                    return newItem;
                })
                .toList();
        CartDto cartDto = modelMapper.map(cart, CartDto.class);
        cartDto.setCartItems(cartItemDtos);
        return cartDto;
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}
