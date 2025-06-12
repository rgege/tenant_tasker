package com.rokas.portfolio.dto;

import java.time.LocalDateTime;

public record TenantResponseDTO (
    Long id,
    String name,
    String tenantKey,
    LocalDateTime createdAt
) {}