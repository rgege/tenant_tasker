package com.rokas.portfolio.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import com.rokas.portfolio.enums.TaskStatus;

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
@Table(name="task")
public class Task {
   @Id 
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private String description;
   private LocalDate dueDate;

   @Enumerated(EnumType.STRING)
   private TaskStatus taskStatus;

   @ManyToOne
   @JoinColumn(name = "tenant_id")
   private Tenant tenant;

   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;

   private LocalDateTime createdAt = LocalDateTime.now();

   
   public Long getId() {
      return this.id;
   }

   public String getTitle() {
      return this.title;
   }

   public LocalDate getDueDate() {
      return this.dueDate;
   }

   public String getDescription() {
      return this.description;
   }

   public TaskStatus getTaskStatus() {
      return this.taskStatus;
   }

   public Tenant getTenant() {
      return this.tenant;
   }

   public User getUser() {
      return this.user;
   }

   public LocalDateTime getCreatedAt() {
      return this.createdAt;
   }



   public void setTitle(String title) {
      this.title = title;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setDueDate(int year, int month, int day) {
      this.dueDate = LocalDate.of(year, month, day);
   }

   public void setTaskStatus(TaskStatus status) {
      this.taskStatus = status;
   }

   public void setTenant(Tenant tenant) {
      this.tenant = tenant;
   }
   
   public void setUser(User user) {
      this.user = user;
   }
}
