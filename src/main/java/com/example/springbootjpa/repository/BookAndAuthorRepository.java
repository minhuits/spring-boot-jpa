package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.BookAndAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long> { }
