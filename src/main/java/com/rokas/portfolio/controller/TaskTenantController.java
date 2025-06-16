package com.rokas.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.service.TaskTenantService;

@RestController
@RequestMapping("/api/tasks")
public class TaskTenantController {
   
    private final TaskTenantService taskTenantService;

    public TaskTenantController(TaskTenantService taskTenantService) {
        this.taskTenantService = taskTenantService;
    }

    @GetMapping
    List<Task> listTasks() {
        return taskTenantService.listTasksUnderTenant();
    }
}
