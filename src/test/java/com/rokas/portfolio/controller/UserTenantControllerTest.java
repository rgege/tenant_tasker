package com.rokas.portfolio.controller;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.service.UserTenantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserTenantController.class)
public class UserTenantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserTenantService userTenantService;

    @Test
    public void testListUsers() throws Exception {
        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");

        User user2 = new User();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(user1);
        mockUsers.add(user2);

        when(userTenantService.listAllUsersUnderTenant()).thenReturn(mockUsers);

        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(mockUsers.size()))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].email").value("alice@example.com"))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[1].email").value("bob@example.com"));
    }
}
