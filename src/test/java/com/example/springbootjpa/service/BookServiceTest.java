package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.Book;
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
        try {
            bookService.put();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest2() {
        try {
            bookService.putBookAndAuthor2();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest3() {
        try {
            bookService.putBookAndAuthor3();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest4() {
        try {
            bookService.putBookAndAuthor4();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest5() {
        try {
            bookService.putBookAndAuthor5();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest6() {
        try {
            bookService.putBookAndAuthor6();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void TransactionTest7() {
        try {
            bookService.putBookAndAuthor7();
        } catch (RuntimeException e) {
            System.out.println(">>> " + e.getMessage());
        }

        System.out.println("Books : " + bookRepository.findAll());
        System.out.println("Authors : " + authorRepository.findAll());
    }

    @Test
    void isolationTest() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println("(Test) >>> " + bookRepository.findAll());

    }

    @Test
    void isolationTest2() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get2(1L);

        System.out.println("(Test) >>> " + bookRepository.findAll());

    }

    @Test
    void isolationTest3() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get3(1L);

        System.out.println("(Test) >>> " + bookRepository.findAll());

    }

    @Test
    void isolationTest4() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get4(1L);

        System.out.println("(Test) >>> " + bookRepository.findAll());

    }
}