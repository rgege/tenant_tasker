package com.rokas.portfolio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rokas.portfolio.dto.TaskRequestDTO;
import com.rokas.portfolio.dto.TaskResponseDTO;
import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.TaskStatus;
import com.rokas.portfolio.repository.TaskRepository;
import com.rokas.portfolio.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TenantService tenantService;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void shouldCreateTask() {
        TaskRequestDTO request = new TaskRequestDTO("test_title",
                                                    "test_description",
                                                    LocalDate.of(2025,07,01),
                                                    TaskStatus.ASSIGNED,
                                                    "test_tenantKey",
                                                    "test_userName");
                                                    

        Tenant tenant = new Tenant();
        tenant.setTenantKey(request.getTenantKey());

        User user = new User();
        user.setName(request.getUserName());

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescrption());
        task.setDueDate(request.getDueDate());
        task.setTaskStatus(request.getTaskStatus());
        task.setUser(user);
        task.setTenant(tenant);

        when(tenantService.getCurrentTenant()).thenReturn(tenant);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(userRepository.findByNameAndTenant_TenantKey("test_userName", "test_tenantKey"))
            .thenReturn(user);

        TaskResponseDTO response = taskService.createTask(request);

        assertThat(response.title()).isEqualTo("test_title");
        assertThat(response.description()).isEqualTo("test_description");
        assertThat(response.dueDate()).isEqualTo(request.getDueDate());
        assertThat(response.tenantKey()).isEqualTo("test_tenantKey");
        assertThat(response.userName()).isEqualTo("test_userName");
        assertThat(response.createdAt()).isNotNull();
        verify(taskRepository).save(any(Task.class));
    }
}