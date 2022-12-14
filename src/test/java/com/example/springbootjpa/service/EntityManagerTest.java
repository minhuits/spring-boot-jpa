package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
public class EntityManagerTest {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest() {
        String query = "select u from User u";
        System.out.println(entityManager.createQuery(query).getResultList());
    }

    @Test
    void cacheFindTest() {
//        System.out.println(userRepository.findByEmail("martin@fastcmpus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcmpus.com"));
//        System.out.println(userRepository.findByEmail("martin@fastcmpus.com"));
//
//        System.out.println(userRepository.findById(2L).orElseThrow(RuntimeException::new));
//        System.out.println(userRepository.findById(2L).orElseThrow(RuntimeException::new));
//        System.out.println(userRepository.findById(2L).orElseThrow(RuntimeException::new));

        userRepository.deleteById(1L);
    }

    @Test
    void cacheFindTest2() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        user.setName("test");
        userRepository.save(user);

        System.out.println("===========================");

        user.setEmail("test@naver.com");
        userRepository.save(user);

        System.out.println(userRepository.findAll()); // select * from user
    }
}
