package com.dailycodework.dreamshops.repositories;

import com.dailycodework.dreamshops.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
}
