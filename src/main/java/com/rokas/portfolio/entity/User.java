package com.rokas.portfolio.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.rokas.portfolio.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

    private boolean enabled;
    private LocalDateTime createdAt = LocalDateTime.now();


    public Tenant getTenant() {
        return this.tenant;
    }
    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Role getRole() {
        return this.role;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(Boolean bool) {
        this.enabled = bool;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }
}
