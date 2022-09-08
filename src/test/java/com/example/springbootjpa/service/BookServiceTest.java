package com.example.springbootjpa.service;

import com.example.springbootjpa.repository.AuthorRepository;
import com.example.springbootjpa.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void TransactionTest() {
        bookService.putBookAndAuthor();

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }
}