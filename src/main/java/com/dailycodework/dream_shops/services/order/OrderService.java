package com.dailycodework.dream_shops.services.order;

import com.dailycodework.dream_shops.enums.OrderStatus;
import com.dailycodework.dream_shops.exception.ResourceNotFoundException;
import com.dailycodework.dream_shops.models.Cart;
import com.dailycodework.dream_shops.models.Order;
import com.dailycodework.dream_shops.models.OrderItem;
import com.dailycodework.dream_shops.models.Product;
import com.dailycodework.dream_shops.repositories.OrderRepository;
import com.dailycodework.dream_shops.repositories.ProductRepository;
import com.dailycodework.dream_shops.services.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ICartService cartService;
    @Override
    public Order placeOrder(Long userId) {
        Cart cart = cartService.getCartByUserId(userId);
        Order order = createOrder(cart);
        List<OrderItem> orderItems = createOrderItems(order, cart);
        order.setOrderItems(new HashSet<>(orderItems));
        order.setTotalAmount(calculateTotalAmount(orderItems));
        Order savedOrder = orderRepository.save(order);
        cartService.clearCart(cart.getId());
        return savedOrder;
    }

    private Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    private List<OrderItem> createOrderItems(Order order, Cart cart){
        return cart.getCartItems().stream().map(cartItem -> {
            Product product = cartItem.getProduct();
            product.setInventory(product.getInventory() - cartItem.getQuantity());
            productRepository.save(product);
            return new OrderItem(order, product, cartItem.getQuantity(), cartItem.getUnitPrice());
        }).toList();
    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItems){
        return orderItems.stream()
                .map(item -> item.getPrice()
                        .multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
    }

    @Override
    public List<Order> getUserOrders(Long userId){
        return orderRepository.findByUserId(userId);
    }
}
