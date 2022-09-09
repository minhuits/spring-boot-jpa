package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.Author;
import com.example.springbootjpa.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void putAuthor2() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }

    @Transactional(propagation = Propagation.NESTED)
    public void putAuthor3() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthorError() throws RuntimeException {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("(REQUIRED) Author Service Error!!");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void putAuthorError2() {
        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("(REQUIRES_NEW) Author Service Error!!");
    }
}