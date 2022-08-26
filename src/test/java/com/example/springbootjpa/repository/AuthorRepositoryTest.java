package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Author;
import com.example.springbootjpa.domain.Book;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = getBook("책1");
        Book book2 = getBook("책2");
        Book book3 = getBook("개발책1");
        Book book4 = getBook("개발책2");

        Author author1 = getAuthor("martin");
        Author author2 = getAuthor("steve");

        book1.addAuthor(author1);
        book2.addAuthor(author2);
        book3.addAuthor(author1, author2);
        book4.addAuthor(author1, author2);

        author1.addBook(book1, book3, book4);
        author2.addBook(book2, book3, book4);

        List<Book> books = Lists.newArrayList(book1, book2, book3, book4);
        List<Author> authors = Lists.newArrayList(author1, author2);

        bookRepository.saveAll(books);
        authorRepository.saveAll(authors);

        System.out.println("Authors through book : " + bookRepository.findAll().get(2).getAuthors());
        System.out.println("Authors through author : " + authorRepository.findAll().get(0).getBooks());
    }

    private Book getBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author getAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }
}