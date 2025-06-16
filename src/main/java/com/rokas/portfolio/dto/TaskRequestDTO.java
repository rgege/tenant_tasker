package com.rokas.portfolio.dto;

import java.time.LocalDate;

import com.rokas.portfolio.enums.TaskStatus;

public class TaskRequestDTO {
   private String title;
   private String description;
   private LocalDate dueDate;
   private TaskStatus taskStatus;
   private String tenantKey;
   private String userName; 

   public TaskRequestDTO() {};

   public TaskRequestDTO(String title,
                         String description,
                         LocalDate dueDate,
                         TaskStatus taskStatus,
                         String tenantKey,
                         String userName) {
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.taskStatus = taskStatus;
    this.tenantKey = tenantKey;
    this.userName = userName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void seTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setTenantId(String tenantKey) {
        this.tenantKey = tenantKey;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescrption() {
        return this.description;
    }

    public LocalDate getDueDate() {
        return this.dueDate;
    }

    public TaskStatus getTaskStatus() {
        return this.taskStatus;
    }

    public String getTenantKey() {
        return this.tenantKey;
    }

    public String getUserName() {
        return this.userName;
    }
}
