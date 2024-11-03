package com.springboot.springdata50jpa.repository;

import com.springboot.springdata50jpa.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}