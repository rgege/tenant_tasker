package com.rokas.portfolio.dto;

import java.util.Objects;

import com.rokas.portfolio.entity.Tenant;
import com.rokas.portfolio.enums.Role;

public class UserRequestDTO {
    private String name;
    private String email;
    private String password;

    public UserRequestDTO() {}

    public UserRequestDTO(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    } 

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password; 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRequestDTO)) return false;
        UserRequestDTO dto = (UserRequestDTO) o;
        return Objects.equals(name, dto.name) &&
               Objects.equals(email, dto.email);
    }
}


