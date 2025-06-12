package com.rokas.portfolio.dto;

import java.util.Objects;

public class TenantRequestDTO {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TenantRequestDTO)) return false;
        TenantRequestDTO that = (TenantRequestDTO) o;
        return Objects.equals(name, that.name) &&
               Objects.equals(tenantKey, that.tenantKey);
    }
}


