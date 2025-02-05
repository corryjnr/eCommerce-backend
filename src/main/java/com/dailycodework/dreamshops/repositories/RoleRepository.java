package com.dailycodework.dreamshops.repositories;

import com.dailycodework.dreamshops.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);

    boolean existsByName(String role);
}
