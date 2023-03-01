package com.spring.blog.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.models.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUserName(String username);
}
