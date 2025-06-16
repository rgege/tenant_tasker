package com.rokas.portfolio.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;


import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.repository.TaskRepository;

@ExtendWith(MockitoExtension.class)
public class TaskTenantServiceTest {
    @Mock
    private TenantService tenantService;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskTenantService taskTenantService;

    @Test
    public void shouldReturnListOfTaskUnderTenant() {
        Tenant tenant = new Tenant();
        tenant.setTenantKey("test_tenantKey");

        Task task1 = new Task();
        task1.setTitle("title1");

        Task task2 = new Task();
        task2.setTitle("title2");

        List<Task> expected = List.of(task1, task2);

        when(tenantService.getCurrentTenant()).thenReturn(tenant);
        when(taskRepository.findByTenant_TenantKey(tenant.getTenantKey())).thenReturn(expected);
        
        List<Task> result = taskTenantService.listTasksUnderTenant();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getTitle()).isEqualTo("title1");
        assertThat(result.get(1).getTitle()).isEqualTo("title2");

        verify(tenantService).getCurrentTenant();
        verify(taskRepository).findByTenant_TenantKey("test_tenantKey");
    }
}
