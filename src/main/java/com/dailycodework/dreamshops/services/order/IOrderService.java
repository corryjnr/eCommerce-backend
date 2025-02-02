package com.dailycodework.dreamshops.services.order;

import com.dailycodework.dreamshops.models.Order;

import java.util.List;

public interface IOrderService{
    Order placeOrder(Long userId);
    Order getOrder(Long orderId);

    List<Order> getUserOrders(Long userId);
}
