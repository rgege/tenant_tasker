package com.rokas.portfolio.dto;

import java.time.LocalDateTime;

import com.rokas.portfolio.enums.Role;

public record UserResponseDTO (
    Long id,
    String name,
    String email,
    Role role,
    String tenantKey,
    Boolean enabled,
    LocalDateTime createdAt
) {}