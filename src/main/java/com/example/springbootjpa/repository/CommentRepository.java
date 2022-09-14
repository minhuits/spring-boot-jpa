package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
