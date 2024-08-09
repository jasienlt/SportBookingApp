package com.developer.sportbooking.persistence.repository;

import com.developer.sportbooking.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {

    Role findByName(String name);

    @Override
    void delete(Role role);

}