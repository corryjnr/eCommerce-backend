package com.dailycodework.dreamshops.services.cart;

import com.dailycodework.dreamshops.dto.*;
import com.dailycodework.dreamshops.exception.ResourceNotFoundException;
import com.dailycodework.dreamshops.models.*;
import com.dailycodework.dreamshops.repositories.CartItemRepository;
import com.dailycodework.dreamshops.repositories.CartRepository;
import com.dailycodework.dreamshops.repositories.UserRepository;
import com.dailycodework.dreamshops.services.product.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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
        cart.updateTotalAmount();
    }

    @Override
    public BigDecimal getTotalAmount(Long cartId) {
        Cart cart = getCart(cartId);
        return cart.getTotalAmount();
    }

    @Override
    public Cart initializeNewCart(User user){
        return Optional.ofNullable(getCartByUserId(user.getId()))
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
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
