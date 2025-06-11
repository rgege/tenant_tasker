package com.rokas.portfolio.dto;

public class TenantResponseDTO {
    private Long id;
    private String name;
    private String tenantKey;

    public TenantResponseDTO(Long id, String name, String tenantKey) {
        this.id = id;
        this.name = name;
        this.tenantKey = tenantKey;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getTenantId() {
        return this.tenantKey;
    }
    
}
