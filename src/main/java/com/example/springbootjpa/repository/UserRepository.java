package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
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

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtAfter(LocalDateTime dateTime);
    List<User> findByCreatedAtGreaterThan(LocalDateTime dateTime);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime dateTime);

    List<User> findByCreatedAtBetween(LocalDateTime dateTime1, LocalDateTime dateTime2);

    List<User> findByIdBetween(Long id1, Long id2);

    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    List<User> findByIdIsNotNull();

//    List<User> findByAddressesIsNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    Set<User> findUserByNameIs(String name);

    Set<User> findUserByName(String name);

    Set<User> findUserByNameEquals(String name);

    List<User> findTopByNameOrderByIdDesc(String name);
    List<User> findTopByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);
}
