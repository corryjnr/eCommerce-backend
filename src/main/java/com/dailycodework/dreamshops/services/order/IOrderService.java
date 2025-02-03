package com.dailycodework.dreamshops.services.order;

import com.dailycodework.dreamshops.dto.OrderDto;
import com.dailycodework.dreamshops.models.Order;

import java.util.List;

public interface IOrderService{
    OrderDto placeOrder(Long userId);
    OrderDto getOrder(Long orderId);

    List<OrderDto> getUserOrders(Long userId);
}
