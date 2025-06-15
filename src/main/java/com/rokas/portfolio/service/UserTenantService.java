package com.rokas.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.repository.UserRepository;

@Service
public class UserTenantService {

    private final UserRepository userRepository;
    private final TenantService tenantService;

    public UserTenantService(TenantService tenantService, UserRepository userRepository) {
        this.tenantService = tenantService;
        this.userRepository = userRepository;
    }
    
    public List<User> listAllUsersUnderTenant() {
        Tenant tenant = tenantService.getCurrentTenant();
        List<User> userList = userRepository.findByTenant_TenantKey(tenant.getTenantKey());
        return userList;
    }
}


