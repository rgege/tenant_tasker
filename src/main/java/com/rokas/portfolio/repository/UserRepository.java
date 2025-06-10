package com.rokas.portfolio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rokas.portfolio.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
}