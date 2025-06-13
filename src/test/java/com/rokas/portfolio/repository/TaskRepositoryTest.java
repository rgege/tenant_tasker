package com.rokas.portfolio.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.TaskStatus;
import com.rokas.portfolio.repository.TaskRepository;
import com.rokas.portfolio.repository.TenantRepository;
import com.rokas.portfolio.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TaskRepositoryTest {
    
    @Autowired
    TaskRepository taskRepository;

    @Autowired 
    UserRepository userRepository;

    @Autowired
    TenantRepository tenantRepository;

    private Task createTestTask() {
        Task task = new Task();
        task.setTitle("test_title");
        task.setDescription("test_description");
        task.setDueDate(2025, 12, 31);
        task.setTaskStatus(TaskStatus.ASSIGNED);

        return task;
    }

    private User createTestUser() {
        User user = new User();
        return user;
    }

    private Tenant createTestTenant() {
        Tenant tenant = new Tenant();
        return tenant;
    }

    @Test
    void shouldSaveTaskToDB() {
        Task savedTask = taskRepository.save(createTestTask());
        
        assertNotNull(savedTask.getId());
        assertEquals("test_title", savedTask.getTitle());
        assertEquals("test_description", savedTask.getDescription());

        LocalDate date = LocalDate.of(2025, 12, 31);
        assertEquals(date, savedTask.getDueDate());

        TaskStatus taskStatus = TaskStatus.ASSIGNED;
        assertEquals(taskStatus, savedTask.getTaskStatus());

        assertNotNull(savedTask.getCreatedAt());

        Task found = taskRepository.findById(savedTask.getId()).orElseThrow();
        assertEquals("test_title", found.getTitle());
    }

    @Test 
    void TaskShouldHaveTenantAndUserID() {
        Tenant tenant = createTestTenant();

        User user = createTestUser();
        user.setTenant(tenant);
        
        Task task = taskRepository.save(createTestTask());
        task.setUser(user);
        task.setTenant(tenant);

        Tenant savedTenant = tenantRepository.save(tenant);
        User savedUser = userRepository.save(user);
        Task savedTask = taskRepository.save(task);

        Tenant foundTenant = tenantRepository.findById(savedTenant.getId()).orElseThrow();
        User foundUser = userRepository.findById(savedUser.getId()).orElseThrow();
        Task foundTask = taskRepository.findById(savedTask.getId()).orElseThrow();

        assertNotNull(foundTask.getTenant());
        assertNotNull(foundTask.getUser());

        assertEquals(foundTenant.getId(), foundTask.getTenant().getId());
        assertEquals(foundUser.getId(), foundTask.getUser().getId());
    }
}
