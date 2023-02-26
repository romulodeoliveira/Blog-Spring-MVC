package com.spring.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
