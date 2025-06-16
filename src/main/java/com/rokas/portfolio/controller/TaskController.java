package com.rokas.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.dto.TaskRequestDTO;
import com.rokas.portfolio.dto.TaskResponseDTO;
import com.rokas.portfolio.repository.UserRepository;
import com.rokas.portfolio.service.TaskService;
import com.rokas.portfolio.service.TenantService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private final TaskService taskService;
    //private final TenantService tenantService;
    //private final UserRepository userRepository;

    public TaskController(TaskService taskService,
                          TenantService tenantService,
                          UserRepository userRepository) {
        this.taskService = taskService;
        //this.tenantService = tenantService;
        //this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody TaskRequestDTO request) {
        TaskResponseDTO created = taskService.createTask(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
