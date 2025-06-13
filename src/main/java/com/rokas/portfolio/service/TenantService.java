package com.rokas.portfolio.service;

import org.springframework.stereotype.Service;

import com.rokas.portfolio.config.TenantContext;
import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
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

   public TenantResponseDTO createTenant(TenantRequestDTO request) {
        Tenant tenant = new Tenant();
        tenant.setName(request.getName());
        tenant.setTenantKey(request.getTenantKey());

        Tenant savedTenant = tenantRepository.save(tenant);
        
        return new TenantResponseDTO(
            savedTenant.getId(),
            savedTenant.getName(),
            savedTenant.getTenantKey(),
            savedTenant.getCreatedAt()
        );
     }
}
