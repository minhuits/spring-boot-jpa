package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.domain.Publisher;
import com.example.springbootjpa.domain.Review;
import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.dto.BookNameAndCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
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
        if (!bookOptional.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");

        Book book1 = bookOptional.get();
        book1.getPublisher().setName("슬로우 캠퍼스");

        bookRepository.save(book1);

        System.out.println("Publisher Lists : " + publisherRepository.findAll());
    }

    @Test
    void bookCascadeTest3() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("Book List : " + bookRepository.findAll());
        System.out.println("Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional = bookRepository.findById(1L);
        if (!bookOptional.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");

        Book book1 = bookOptional.get();
        book1.getPublisher().setName("슬로우 캠퍼스");

        bookRepository.save(book1);

        System.out.println("Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional1 = bookRepository.findById(1L);
        if (!bookOptional1.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");

        Book book2 = bookOptional1.get();
        bookRepository.delete(book2);

        System.out.println("(Delete) Book List : " + bookRepository.findAll());
        System.out.println("(Delete) Publisher List : " + publisherRepository.findAll());
    }

    @Test
    void bookCascadeTest4() {
        Book book = new Book();
        book.setName("JPA 초격차 패키지");

        Publisher publisher = new Publisher();
        publisher.setName("패스트캠퍼스");

        book.setPublisher(publisher);
        bookRepository.save(book);

        System.out.println("Book List : " + bookRepository.findAll());
        System.out.println("Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional = bookRepository.findById(1L);
        if (!bookOptional.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");

        Book book1 = bookOptional.get();
        book1.getPublisher().setName("슬로우 캠퍼스");

        bookRepository.save(book1);

        System.out.println("Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional2 = bookRepository.findById(1L);
        if (!bookOptional2.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");
        Book book3 = bookOptional2.get();
        book3.setPublisher(null);

        bookRepository.save(book3);

        System.out.println("(Delete) Book List : " + bookRepository.findAll());
        System.out.println("(Delete) Publisher List : " + publisherRepository.findAll());

        Optional<Book> bookOptional3 = bookRepository.findById(1L);
        if (!bookOptional3.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");
        System.out.println("(Publisher) book3 Publisher : " + bookOptional3.get().getPublisher());
    }

    @Test
    void bookRemoveCascadeTest() {
        bookRepository.deleteById(1L);

        System.out.println("Book List : " + bookRepository.findAll());
        System.out.println("Publisher List : " + publisherRepository.findAll());

        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void softDelete() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));
        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }

    @Test
    void softDelete2() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

        bookRepository.findByCategoryIsNull().forEach(System.out::println);
        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
    }

    @Test
    void softDelete3() {
        bookRepository.findAll().forEach(System.out::println);
        System.out.println(bookRepository.findById(3L));

        bookRepository.findByCategoryIsNull().forEach(System.out::println);
    }

    @Test
    void queryTest() {
        bookRepository.findAll().forEach(System.out::println);

        String name = "Spring JPA 패키지";
        LocalDateTime dateTime = LocalDateTime.now().minusDays(1L);

        List<Book> books = bookRepository
                .findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                        name, dateTime, dateTime
                );
        String title = "findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : ";
        System.out.println(title + books);

        List<Book> books1 = bookRepository.findByNameRecently(name, dateTime, dateTime);
        System.out.println("findByNameRecently : " + books1);

        List<BookNameAndCategory> books2 = bookRepository.findBookNameAndCategory();
        System.out.println("BookNameAndCategory : " + books2);

        bookRepository.findBookNameAndCategory().forEach(
                book -> System.out.println(book.getName() + " : " + book.getCategory()));

        PageRequest pageRequest = PageRequest.of(1, 1);
        bookRepository.findBookNameAndCategory(pageRequest).forEach(bookNameAndCategory ->
                System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory())
        );

        PageRequest pageRequest2 = PageRequest.of(0, 1);
        bookRepository.findBookNameAndCategory(pageRequest2).forEach(bookNameAndCategory ->
                System.out.println(bookNameAndCategory.getName() + " : " + bookNameAndCategory.getCategory())
        );
    }

    @Test
    void nativeQueryTest() {
        bookRepository.findAll().forEach(System.out::println);
        bookRepository.findAllCustom().forEach(System.out::println);
    }

    @Test
    void nativeQueryTest2() {
        List<Book> books = bookRepository.findAll();

        for (Book book : books) {
            book.setCategory("IT 전문서");
        }

        bookRepository.saveAll(books);

        bookRepository.findAll().forEach(System.out::println);

        System.out.println("affected row : " + bookRepository.updateCategories());

        bookRepository.findAllCustom().forEach(System.out::println);

        System.out.println("show tables : " + bookRepository.showTables());
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