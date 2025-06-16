package com.rokas.portfolio.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.TaskStatus;

public record TaskResponseDTO (
    Long id,
    String title,
    String description,
    LocalDate dueDate,
    String tenantKey,
    String userName,
    TaskStatus taskStatus,
    LocalDateTime createdAt
) {}