package com.rokas.portfolio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rokas.portfolio.dto.UserRequestDTO;
import com.rokas.portfolio.dto.UserResponseDTO;
import com.rokas.portfolio.service.TenantService;
import com.rokas.portfolio.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private TenantService tenantService;
    private UserService userService;

    public UserController(UserService userService, TenantService tenantService) {
        this.userService = userService;
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO request) {
        UserResponseDTO created = userService.createUser(request, tenantService);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
