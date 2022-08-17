package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll() {
        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    @Test
    void findAllSort() {
        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        users.forEach(System.out::println);
    }

    @Test
    void findAllById() {
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 2L, 5L));
        users.forEach(System.out::println);
    }

    @Test
    void saveAll() {
        User user1 = new User("jack", "jack@fastcampus.com");
        User user2 = new User("steve", "steve@fastcampus.com");

        List<User> users = userRepository.saveAll(Lists.newArrayList(user1, user2));
        users.forEach(System.out::println);
    }

    @Test
    void save() {
        User user1 = new User("jack", "jack@fastcampus.com");

        userRepository.save(user1);

        List<User> users = userRepository.findAll();

        users.forEach(System.out::println);
    }

    @Test
    @Transactional
    void getOne() {
        User user = userRepository.getReferenceById(1L);

        System.out.println(user);
    }

    @Test
    void findById() {
        User user = userRepository.findById(1L).orElse(null);

        System.out.println(user);
    }
}