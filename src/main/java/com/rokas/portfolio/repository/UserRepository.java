package com.rokas.portfolio.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rokas.portfolio.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByTenant_TenantKey(String tenantKey);
    User findByNameAndTenant_TenantKey(String name, String tenantKey);
}