package com.dailycodework.dreamshops.controllers;

import com.dailycodework.dreamshops.exception.ResourceNotFoundException;
import com.dailycodework.dreamshops.models.Cart;
import com.dailycodework.dreamshops.models.User;
import com.dailycodework.dreamshops.response.ApiResponse;
import com.dailycodework.dreamshops.services.cart.ICartItemService;
import com.dailycodework.dreamshops.services.cart.ICartService;
import com.dailycodework.dreamshops.services.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId,
                                                     @RequestParam int quantity){
        User user = userService.getUserById(4L);
        Cart cart = cartService.initializeNewCart(user);
        cartItemService.addItemToCart(cart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Add item success!", null));
    }

    @DeleteMapping("/cart/{cartId}/item/{productId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable  Long cartId,
                                                          @PathVariable Long productId){
        try {
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Remove item success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/cart/{cartId}/item/{productId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long productId,
                                                          @RequestParam int quantity){
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update item quantity success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }
}
