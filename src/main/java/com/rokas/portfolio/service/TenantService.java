package com.rokas.portfolio.service;

import org.springframework.stereotype.Service;

import com.rokas.portfolio.config.TenantContext;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;

@Service
public class TenantService {
   private final TenantRepository tenantRepository;

   public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
   }

   public Tenant getCurrentTenant() {
        String tenantKey = TenantContext.getTenantId();
        return tenantRepository.findByTenantKey(tenantKey)
                .orElseThrow(() -> new IllegalStateException("Tenant not found"));
   }
}
