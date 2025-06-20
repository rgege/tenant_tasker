package com.rokas.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
import com.rokas.portfolio.service.TenantService;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {
    
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<TenantResponseDTO> createTenant(@RequestBody TenantRequestDTO request) {
        TenantResponseDTO created = tenantService.createTenant(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
