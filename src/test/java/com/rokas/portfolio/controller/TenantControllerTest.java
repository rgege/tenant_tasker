package com.rokas.portfolio.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.notNullValue;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.TenantResponseDTO;
import com.rokas.portfolio.service.TenantService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;


@WebMvcTest(TenantController.class)
class TenantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenantService tenantService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldcreateTenantWithPost() throws Exception {
        TenantRequestDTO request = new TenantRequestDTO("name", "key");
        TenantResponseDTO response = new TenantResponseDTO(1L, "name", "key", LocalDateTime.now());

        when(tenantService.createTenant(any(TenantRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/tenants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated()) // checks for 201
                .andExpect(jsonPath("$.id").value(response.id()))
                .andExpect(jsonPath("$.name").value("name"))
                .andExpect(jsonPath("$.tenantKey").value("key"))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }
}
