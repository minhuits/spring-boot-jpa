package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

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

    @Test
    void flush() {
        userRepository.save(new User("new martin", "newMartin@fastcampus.com"));

        userRepository.flush();

        userRepository.findAll().forEach(System.out::println);
    }
    @Test
    void saveAndFlush() {
        userRepository.saveAndFlush(new User("new martin", "newMartin@fastcampus.com"));

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void count() {
        long count = userRepository.count();

        System.out.println(count);
    }
    @Test
    void existsById() {
        boolean exists = userRepository.existsById(1L);

        System.out.println(exists);
    }

    @Test
    void findByIdDelete() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        userRepository.delete(user);
    }
    @Test
    void deleteById() {
        userRepository.deleteById(1L);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void deleteAll() {
        userRepository.deleteAll();

        userRepository.findAll().forEach(System.out::println);
    }
    @Test
    void findByIdDeleteAll() {
        List<User> user = userRepository.findAllById(Lists.newArrayList(1L, 3L));

        userRepository.deleteAll(user);

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void findByIdDeleteInBatch() {
        List<User> user = userRepository.findAllById(Lists.newArrayList(1L, 3L));

        userRepository.deleteAllInBatch(user);

        userRepository.findAll().forEach(System.out::println);
    }
    @Test
    void deleteAllInBatch() {
        userRepository.deleteAllInBatch();

        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void pageRequest() {
        PageRequest pageRequest = PageRequest.of(0, 3);

        Page<User> users = userRepository.findAll(pageRequest);

        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println);
    }

    @Test
    void exampleMatcher() {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());

        User newUser = new User("ma", "fastcmpus.com");

        Example<User> example = Example.of(newUser, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }
    @Test
    void exampleMatcher2() {
        User user = new User();
        user.setEmail("slow");

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());

        Example<User> example = Example.of(user, matcher);

        userRepository.findAll(example).forEach(System.out::println);
    }
}