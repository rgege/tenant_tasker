package com.rokas.portfolio.dto;

public class TenantRequestDTO {
    private Long id;
    private String name;
    private String tenantKey;

    public TenantRequestDTO() {}

    public TenantRequestDTO(String name, String tenantKey) {
        this.name = name;
        this.tenantKey = tenantKey;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setTenantKey(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public String getName() {
        return this.name;
    }

    public String getTenantKey() {
        return this.tenantKey;
    }
}


