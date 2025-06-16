package com.rokas.portfolio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.notNullValue;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rokas.portfolio.dto.TaskRequestDTO;
import com.rokas.portfolio.dto.TaskResponseDTO;
import com.rokas.portfolio.enums.TaskStatus;
import com.rokas.portfolio.repository.UserRepository;
import com.rokas.portfolio.service.TaskService;
import com.rokas.portfolio.service.TenantService;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean 
    private TenantService tenantService;

    @MockitoBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldCreateTaskWithPost() throws Exception {
        TaskRequestDTO request = new TaskRequestDTO("test_title",
                                                    "test_description",
                                                    LocalDate.of(2025,07,01),
                                                    TaskStatus.ASSIGNED,
                                                    "test_tenantKey",
                                                    "test_userName");

        TaskResponseDTO response = new TaskResponseDTO(1L,
                                                       "test_title",
                                                       "test_description",
                                                       LocalDate.of(2025,07,01),
                                                       "test_tenantKey",
                                                       "test_userName",
                                                       TaskStatus.ASSIGNED,
                                                       LocalDateTime.now()
                                                       );

    when(taskService.createTask(any(TaskRequestDTO.class))).thenReturn(response);

    mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated()) // checks for 201
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.title").value(response.title()))
                .andExpect(jsonPath("$.description").value(response.description()))
                .andExpect(jsonPath("$.dueDate").value(response.dueDate().toString()))
                .andExpect(jsonPath("$.taskStatus").value(response.taskStatus().toString()))
                .andExpect(jsonPath("$.tenantKey").value(response.tenantKey()))
                .andExpect(jsonPath("$.userName").value(response.userName()))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }
}
