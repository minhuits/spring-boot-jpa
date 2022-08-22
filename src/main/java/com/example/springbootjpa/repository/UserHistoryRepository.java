package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {}
