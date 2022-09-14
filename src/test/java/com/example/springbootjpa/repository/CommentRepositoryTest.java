package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest() {
        Comment comment = new Comment();
        comment.setComment("별로예요");
//         comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear();

//        Comment comment1 = commentRepository.findById(3L).orElseThrow(RuntimeException::new);
//
//        System.out.println(comment1);

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);
    }
}