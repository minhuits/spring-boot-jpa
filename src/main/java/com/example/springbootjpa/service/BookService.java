package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.Author;
import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.repository.AuthorRepository;
import com.example.springbootjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }
}
