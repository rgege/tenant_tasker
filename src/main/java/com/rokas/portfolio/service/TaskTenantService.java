package com.rokas.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TaskRepository;

@Service
public class TaskTenantService {

    private final TaskRepository taskRepository;
    private final TenantService tenantService;

    public TaskTenantService(TaskRepository taskRepository, TenantService tenantService) {
        this.taskRepository = taskRepository;
        this.tenantService = tenantService;
    }

    public List<Task> listTasksUnderTenant() {
        Tenant tenant = tenantService.getCurrentTenant();
        List<Task> tasksList = taskRepository.findByTenant_TenantKey(tenant.getTenantKey());
        return tasksList;
    }
}
