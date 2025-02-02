package com.dailycodework.dreamshops.repositories;

import com.dailycodework.dreamshops.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
