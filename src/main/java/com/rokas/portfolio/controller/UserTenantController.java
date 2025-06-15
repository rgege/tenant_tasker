package com.rokas.portfolio.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.entity.User;
import com.rokas.portfolio.service.TenantService;
import com.rokas.portfolio.service.UserTenantService;

@RestController
@RequestMapping("/api/users")
public class UserTenantController {
    private UserTenantService userTenantService;

    public UserTenantController(UserTenantService userTenantService) {
        this.userTenantService = userTenantService;
    }

    @GetMapping
    public List<User> listUsers () {
        return userTenantService.listAllUsersUnderTenant();
    }
}
