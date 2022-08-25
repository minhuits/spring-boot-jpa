package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
}
