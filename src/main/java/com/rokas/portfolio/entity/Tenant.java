package com.rokas.portfolio.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tennant")
public class Tenant {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String name;
   private String tenantKey;
   private LocalDateTime createdAt = LocalDateTime.now();

   public void setName(String name) {
      this.name = name;
   }

   public void setIdentifier(String tenantKey) {
      this.tenantKey = tenantKey;
   }

   public Long getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public LocalDateTime getCreatedAt() {
      return this.createdAt;
   }
}
