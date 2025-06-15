package com.rokas.portfolio.controller;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rokas.portfolio.dto.TenantRequestDTO;
import com.rokas.portfolio.dto.UserRequestDTO;
import com.rokas.portfolio.dto.UserResponseDTO;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.enums.Role;
import com.rokas.portfolio.service.TenantService;
import com.rokas.portfolio.service.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TenantService tenantService;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateUserWithPost() throws Exception {
        Tenant tenant = new Tenant();
        tenant.setTenantKey("tenant_key");

        UserRequestDTO request = new UserRequestDTO("test_name", "secret", "test@test.lt");
        UserResponseDTO response = new UserResponseDTO(1L, 
                                                       "test_name", 
                                                       "test@test.com", 
                                                       Role.USER, 
                                                       tenant.getTenantKey(), 
                                                       true, 
                                                       LocalDateTime.now());
                                                     
         when(userService.createUser(any(UserRequestDTO.class), any(TenantService.class))).thenReturn(response);

        // Perform POST request
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("test_name"))
                .andExpect(jsonPath("$.email").value("test@test.com"))
                .andExpect(jsonPath("$.tenantKey").value("tenant_key"))
                .andExpect(jsonPath("$.enabled").value(true))
                .andExpect(jsonPath("$.createdAt", notNullValue()));
    }
    
}
