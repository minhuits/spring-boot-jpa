package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.domain.Publisher;
import com.example.springbootjpa.domain.Review;
import com.example.springbootjpa.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("Jpa 초격차 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        bookRepository.save(book);

        System.out.println(bookRepository.findAll());
    }

    @Test
    @Transactional
    void bookRelationTest() {
        getBookAndReview();

        User user = userRepository.findByEmail("dennis@fastcmpus.com");
        System.out.println("Review : " + user.getReviews());
    }

    @Test
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        bookRepository.save(book);

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        publisherRepository.save(publisher);

        book.setPublisher(publisher);
        bookRepository.save(book);

        publisher.addBook(book);
        publisherRepository.save(publisher);

        System.out.println("Book List : " + bookRepository.findAll());
        System.out.println("Publisher List : " + publisherRepository.findAll());
    }

    @Test
    void bookCascadeTest2() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("Book List : " + bookRepository.findAll());
        System.out.println("Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional = bookRepository.findById(1L);
        if(!bookOptional.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");

        Book book1 = bookOptional.get();
        book1.getPublisher().setName("슬로우 캠퍼스");

        bookRepository.save(book1);

        System.out.println("Publisher Lists : " + publisherRepository.findAll());
    }


    private void getBookAndReview() {
        Publisher publisher = getPublisher();
        Book book = getBook(publisher);
        User user = getUser();

        getReview(user, book);
    }

    private Publisher getPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        return publisherRepository.save(publisher);
    }

    private Book getBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private User getUser() {
        return userRepository.findByEmail("dennis@fastcmpus.com");
    }

    private void getReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("부의 추월차선");
        review.setContent("인생 필독서");
        review.setScore(4.5f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }


}