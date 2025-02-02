package com.dailycodework.dreamshops.repositories;

import com.dailycodework.dreamshops.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}
