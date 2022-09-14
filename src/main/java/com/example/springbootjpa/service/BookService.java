package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.Author;
import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.repository.AuthorRepository;
import com.example.springbootjpa.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    public void put() {
        this.putBookAndAuthor();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("martin");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생해 DB 커밋이 발생하지 않습니다.");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor2() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        authorService.putAuthor();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor3() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        authorService.putAuthor();

       throw new RuntimeException("(REQUIRED) Book Service Error!!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor4() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        try {
            authorService.putAuthorError();
        } catch (RuntimeException ignored) {}

        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor5() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthorError2();
        } catch (RuntimeException ignored) {}
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor6() {

        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthor2();
        } catch (RuntimeException ignored) {}

        throw new RuntimeException("(REQUIRED) Book Service Error!!");
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void putBookAndAuthor7() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthor3();
        } catch (RuntimeException ignored) {}

        throw new RuntimeException("(REQUIRED) Book Service Error!!");
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        Book book = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        book.setName("바뀔까???");
        bookRepository.save(book);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get2(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void get3(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void get4(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        bookRepository.update();

        entityManager.clear();
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        books.forEach(System.out::println);

        return books;
    }
}
