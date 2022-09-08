package com.example.springbootjpa.service;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void putUserRepository() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        userRepository.save(user);
    }
    @Transactional
    public void putEntityManager() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");

        entityManager.persist(user);
        entityManager.detach(user);

        user.setName("newUser2");
        entityManager.merge(user);
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public void putEntityManager2() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@naver.com");
        entityManager.persist(user);

        user.setName("newUser2");
        entityManager.merge(user);

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);
    }
}
