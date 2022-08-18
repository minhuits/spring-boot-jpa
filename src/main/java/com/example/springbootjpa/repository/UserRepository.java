package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    Set<User> findByName(String name);

    User findFirstByName(String name);

    User findTop1ByName(String name);

    List<User> findFirst2ByName(String name);

    List<User> findTop2ByName(String name);

    List<User> findLast1ByName(String name);
}
