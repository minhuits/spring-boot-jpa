package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest() {
        List<Review> reviews = reviewRepository.findAllByFetchJoin();

        System.out.println("전체를 가져왔습니다.");

        System.out.println(reviews.get(0).getComments());

        System.out.println("첫 번째 리뷰의 코멘트을 가져왔습니다.");

        System.out.println(reviews.get(1).getComments());

        System.out.println("두 번째 리뷰의 코멘트을 가져왔습니다.");

        reviews.forEach(System.out::println);
    }

    @Test
    @Transactional
    void reviewTest2() {
        List<Review> reviews = reviewRepository.findAllByEntityGraph();
        reviews.forEach(System.out::println);

        List<Review> reviews2 = reviewRepository.findAll();
        reviews2.forEach(System.out::println);
    }

}