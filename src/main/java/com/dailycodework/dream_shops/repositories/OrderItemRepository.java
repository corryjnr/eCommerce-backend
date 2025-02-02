package com.dailycodework.dream_shops.repositories;

import com.dailycodework.dream_shops.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
