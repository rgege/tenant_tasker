package com.rokas.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TenantRepository;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    
    @Autowired
    private TenantRepository tenantRepository;

    @PostMapping
    public ResponseEntity<TenantResponseDTO> createTenant(@RequestBody TenantRequestDTO dto) {
        Tenant tenant = new Tenant();
        tenant.setName(dto.getName());
        tenant.setTenantKey(dto.getTenantKey());
        Tenant savedTenant = tenantRepository.save(tenant);

        TenantResponseDTO response = new TenantResponseDTO(
            savedTenant.getId(),
            savedTenant.getName(),
            savedTenant.getTenantKey(),
            savedTenant.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }
}
