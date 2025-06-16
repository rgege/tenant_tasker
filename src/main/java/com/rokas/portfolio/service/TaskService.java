package com.rokas.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rokas.portfolio.dto.TaskRequestDTO;
import com.rokas.portfolio.dto.TaskResponseDTO;
import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.enums.TaskStatus;
import com.rokas.portfolio.repository.TaskRepository;
import com.rokas.portfolio.repository.UserRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TenantService tenantService;

    @Autowired
    private UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, TenantService tenantService, UserRepository userRepository) {
         this.taskRepository = taskRepository;
         this.tenantService = tenantService;
         this.userRepository = userRepository;
    }

    TaskResponseDTO createTask(TaskRequestDTO request) {
        Tenant tenant = tenantService.getCurrentTenant();

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescrption());
        task.setDueDate(request.getDueDate());
        task.setTenant(tenant);
        task.setUser(userRepository.findByNameAndTenant_TenantKey(request.getUserName(), tenant.getTenantKey()));
        task.setTaskStatus(TaskStatus.ASSIGNED);

        Task saved = taskRepository.save(task);

        return new TaskResponseDTO(
            saved.getId(),
            saved.getTitle(),
            saved.getDescription(),
            saved.getDueDate(),
            saved.getTenant().getTenantKey(),
            saved.getUser().getName(),
            saved.getTaskStatus(),
            saved.getCreatedAt()
        );
    }
}
