package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Author;
import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.domain.BookAndAuthor;
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

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest() {
        Book book1 = getBook("책1");
        Book book2 = getBook("책2");
        Book book3 = getBook("개발책1");
        Book book4 = getBook("개발책2");

        Author author1 = getAuthor("martin");
        Author author2 = getAuthor("steve");

        BookAndAuthor bookAndAuthor1 = getBookAndAuthor(book1, author1);
        BookAndAuthor bookAndAuthor2 = getBookAndAuthor(book2, author2);

        BookAndAuthor bookAndAuthor3 = getBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = getBookAndAuthor(book3, author2);

        BookAndAuthor bookAndAuthor5 = getBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = getBookAndAuthor(book4, author2);

        book1.addBookAndAuthors(bookAndAuthor1);
        book2.addBookAndAuthors(bookAndAuthor2);

        book3.addBookAndAuthors(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthors(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthors(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthors(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

        List<Book> books = Lists.newArrayList(book1, book2, book3, book4);
        List<Author> authors = Lists.newArrayList(author1, author2);

        bookRepository.saveAll(books);
        authorRepository.saveAll(authors);

        bookRepository.findAll().get(2)
                .getBookAndAuthors()
                .forEach(o->System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0)
                .getBookAndAuthors()
                .forEach(o->System.out.println(o.getBook()));
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

    private BookAndAuthor getBookAndAuthor(Book book, Author author) {
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}