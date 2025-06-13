package com.rokas.portfolio.config;

import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
import com.rokas.portfolio.entity.Tenant;

public class TenantContext {
    private static final ThreadLocal<String> currentTenant = new ThreadLocal<>();

    public static void setTenantId(String tenantId) {
        currentTenant.set(tenantId);
    }

    public static String getTenantId() {
        return currentTenant.get();
    }

    public static void clear() {
        currentTenant.remove();
    }
}
