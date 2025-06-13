package com.rokas.portfolio.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rokas.portfolio.dto.UserRequestDTO;
import com.rokas.portfolio.dto.UserResponseDTO;
import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.Role;
import com.rokas.portfolio.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock 
    private TenantService tenantService;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shoudlCreateUser() {
        UserRequestDTO request = new UserRequestDTO(
                                            "test_name",
                                            "secret",
                                            "test@mail.com");

        Tenant tenant = new Tenant();
        tenant.setTenantKey("test_tenant");

        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setTenant(tenant);
        
        when(tenantService.getCurrentTenant()).thenReturn(tenant);
        when(userRepository.save(any(User.class))).thenReturn(user);

        UserResponseDTO result = userService.createUser(request, tenantService);

        assertThat(result.name()).isEqualTo("test_name");
        assertThat(result.email()).isEqualTo("test@mail.com");
        assertThat(result.role()).isEqualTo(Role.USER);
        assertThat(result.tenantKey()).isEqualTo("test_tenant");

        verify(userRepository).save(any(User.class));
    }
    
}
