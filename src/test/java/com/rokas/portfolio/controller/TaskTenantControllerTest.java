package com.rokas.portfolio.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.rokas.portfolio.entity.Task;
import com.rokas.portfolio.service.TaskTenantService;

@WebMvcTest(TaskTenantController.class)
public class TaskTenantControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskTenantService taskTenantService;

    @Test
    void shouldReturnListOfTasks() throws Exception {
        Task task1 = new Task();
        task1.setTitle("title1");

        Task task2 = new Task();
        task2.setTitle("title2");

        List<Task> mockTasks = new ArrayList<>();
        mockTasks.add(task1);
        mockTasks.add(task2);

        when(taskTenantService.listTasksUnderTenant()).thenReturn(mockTasks);

        mockMvc.perform(get("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(mockTasks.size()))
                .andExpect(jsonPath("$[0].title").value(mockTasks.get(0).getTitle()))
                .andExpect(jsonPath("$[1].title").value(mockTasks.get(1).getTitle()));
        }
    }
