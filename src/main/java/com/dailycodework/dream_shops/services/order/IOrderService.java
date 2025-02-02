package com.dailycodework.dream_shops.services.order;

import com.dailycodework.dream_shops.models.Order;

import java.util.List;

public interface IOrderService{
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
