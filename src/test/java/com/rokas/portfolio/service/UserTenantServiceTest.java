package com.rokas.portfolio.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.junit.jupiter.MockitoExtension;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTenantServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @Mock
    private TenantService tenantService;

    @InjectMocks
    private UserTenantService userTenantService;

    @Test
    void shouldReturnListOfUsersUnderTenant() {
        // Arrange
        Tenant tenant = new Tenant();
        tenant.setTenantKey("tenant_key");

        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");

        User user2 = new User();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");

        List<User> expectedUsers = List.of(user1, user2);

        when(tenantService.getCurrentTenant()).thenReturn(tenant);
        when(userRepository.findByTenant_TenantKey("tenant_key")).thenReturn(expectedUsers);

        List<User> result = userTenantService.listAllUsersUnderTenant();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Alice");
        assertThat(result.get(1).getEmail()).isEqualTo("bob@example.com");

        verify(tenantService).getCurrentTenant();
        verify(userRepository).findByTenant_TenantKey("tenant_key");
    }
}