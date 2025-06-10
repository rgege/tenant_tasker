package com.rokas.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rokas.portfolio.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
   Optional<Task> findById(Long id);
} 