package com.rokas.portfolio.service;


import org.springframework.stereotype.Service;

import com.rokas.portfolio.dto.UserRequestDTO;
import com.rokas.portfolio.dto.UserResponseDTO;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.enums.Role;
import com.rokas.portfolio.repository.UserRepository;

@Service
public class UserService {

    private final TenantService tenantService;
    private final UserRepository userRepository;

    public UserService(TenantService tenantService,
                       UserRepository userRepository) {
        this.tenantService = tenantService;
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO request, TenantService tenantService) {
        
        User user = new User();
        user.setName(request.getName());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);
        user.setEnabled(true);
        user.setTenant(tenantService.getCurrentTenant());
        User savedUser = userRepository.save(user);

        return new UserResponseDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getEmail(),
            savedUser.getRole(),
            savedUser.getTenant().getTenantKey(),
            savedUser.getEnabled(),
            savedUser.getCreatedAt()
        );
    } 
    
    
}
