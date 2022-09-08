package com.example.springbootjpa.service;

import com.example.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void test1() {
        userService.putUserRepository();

        System.out.println(">>>" + userRepository.findByEmail("newUser@naver.com"));
    }

    @Test
    void test2() {
        userService.putEntityManager();

        System.out.println(">>>" + userRepository.findByEmail("newUser@naver.com"));
    }

    @Test
    void test3() {
        userService.putEntityManager2();

        userRepository.findAll().forEach(System.out::println);
    }
}