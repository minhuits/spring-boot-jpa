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

//        Optional<Comment> commentOptional = commentRepository.findById(3L);
//        if (!commentOptional.isPresent()) throw new RuntimeException("오류가 발생했습니다!!");
//
//        System.out.println(commentOptional.get());

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);
    }
}