package com.rokas.portfolio.repository;

import com.rokas.portfolio.entity.Tenant;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TenantRepository extends JpaRepository<Tenant, Long> {
    Optional<Tenant> findById(Long id);
    Optional<Tenant> findByTenantKey(String key);
}