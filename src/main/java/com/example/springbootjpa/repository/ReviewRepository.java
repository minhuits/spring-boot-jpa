package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
