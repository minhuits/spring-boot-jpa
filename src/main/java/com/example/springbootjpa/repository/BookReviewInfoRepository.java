package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.BookReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long> {}
