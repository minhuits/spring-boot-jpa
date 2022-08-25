package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Book;
import com.example.springbootjpa.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Autowired
    private BookRepository bookRepository;

    private Book getBook() {
        Book book = new Book();
        book.setName("JPA 패키지");
        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        return bookRepository.save(book);
    }
    private void getBookReviewInfo() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(getBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>>" + bookReviewInfoRepository.findAll());

    }

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
//        bookReviewInfo.setBookId(1L);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>>" + bookReviewInfoRepository.findAll());
    }

    @Test
    void crudTest2() {
        getBookReviewInfo();

        Book result = bookReviewInfoRepository.findById(1L).orElseThrow(RuntimeException::new).getBook();

        System.out.println(">>>" + result);

        BookReviewInfo result2 = bookRepository.findById(1L).orElseThrow(RuntimeException::new).getBookReviewInfo();

        System.out.println(">>>" + result2);
    }
}