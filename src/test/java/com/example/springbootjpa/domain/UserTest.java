package com.example.springbootjpa.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class UserTest {
    @Test
    void test() {
        User user = new User();
        user.setEmail("test@naver.com");
        user.setName("홍길동");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        System.out.println(">>>" + user);

        User user1 = new User(null, "홍길동1", "test1@naver.com", LocalDateTime.now(), LocalDateTime.now());
        System.out.println(">>>" + user1);

        User user2 = new User("홍길동2", "test2@naver.com");
        System.out.println(">>>" + user2);

        User user3 = User.builder().name("홍길동3").email("test3@naver.com").build();
        System.out.println(">>>" + user3);
    }
}