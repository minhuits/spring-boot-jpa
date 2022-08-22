package com.example.springbootjpa.repository;

import com.example.springbootjpa.domain.Gender;
import com.example.springbootjpa.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    @Test
    void simpleJpaRepositoryTest() {
        User newUser = new User("david", "david@fastcampus.com");

        userRepository.save(newUser);
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-updated@fastcampus.com");

        userRepository.save(user);
    }

    @Test
    void QueryTest1() {
        System.out.println("[QueryTest1] findByEmail : " + userRepository.findByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] getByEmail : " + userRepository.getByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] readByEmail : " + userRepository.readByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] queryByEmail : " + userRepository.queryByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] searchByEmail : " + userRepository.searchByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] streamByEmail : " + userRepository.streamByEmail("martin@fastcmpus.com"));
        System.out.println("[QueryTest1] findUserByEmail : " + userRepository.findUserByEmail("martin@fastcmpus.com"));

        System.out.println("[QueryTest1] findByName : " + userRepository.findByName("dennis"));
        System.out.println("[QueryTest1] findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("[QueryTest1] findFirstByName : " + userRepository.findFirstByName("martin"));
        System.out.println("[QueryTest1] findTop2ByName : " + userRepository.findTop2ByName("martin"));
        System.out.println("[QueryTest1] findFirs2ByName : " + userRepository.findFirst2ByName("martin"));
        System.out.println("[QueryTest1] findLast1ByName : " + userRepository.findLast1ByName("martin"));
    }

    @Test
    void QueryTest2() {
        LocalDateTime nweDate = LocalDateTime.now().minusDays(1L);

        System.out.println("[QueryTest2] findByEmailAndName : "
                + userRepository.findByEmailAndName("martin@fastcmpus.com", "martin"));
        System.out.println("[QueryTest2] findByEmailOrName : "
                + userRepository.findByEmailOrName("martin@fastcmpus.com", "dennis"));

        System.out.println("[QueryTest2] findByIdAfter : " + userRepository.findByIdAfter(4L));
        System.out.println("[QueryTest2] findByCreatedAtAfter : "
                + userRepository.findByCreatedAtAfter(nweDate));
        System.out.println("[QueryTest2] findByCreatedAtGreaterThan : "
                + userRepository.findByCreatedAtGreaterThan(nweDate));
        System.out.println("[QueryTest2] findByCreatedAtGreaterThanEqual : "
                + userRepository.findByCreatedAtGreaterThanEqual(nweDate));

        System.out.println("[QueryTest2] findByCreatedAtBetween : "
                + userRepository.findByCreatedAtBetween(nweDate, LocalDateTime.now().plusDays(1L)));
        System.out.println("[QueryTest2] findByCreatedAtBetween : " + userRepository.findByIdBetween(1L, 3L));
        System.out.println("[QueryTest2] findByIdGreaterThanEqualAndIdLessThanEqual : "
                + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
    }

    @Test
    void QueryTest3() {
        System.out.println("[QueryTest3] findByIdIsNotNull : " + userRepository.findByIdIsNotNull());
//        System.out.println("[QueryTest3] findByAddressesIsNotEmpty : " + userRepository.findByAddressesIsNotEmpty());
        System.out.println("[QueryTest3] findByNameIn : "
                + userRepository.findByNameIn(Lists.newArrayList("dennis", "martin")));

        System.out.println("[QueryTest3] findByNameStartingWith : " + userRepository.findByNameStartingWith("mar"));
        System.out.println("[QueryTest3] findByNameEndingWith : " + userRepository.findByNameEndingWith("tin"));
        System.out.println("[QueryTest3] findByNameContains : " + userRepository.findByNameContains("art"));
        System.out.println("[QueryTest3] findByNameLike : " + userRepository.findByNameLike("%art%"));

        System.out.println("[QueryTest3] findUserByNameIs : " + userRepository.findUserByNameIs("martin"));
        System.out.println("[QueryTest3] findUserByName : " + userRepository.findUserByName("martin"));
        System.out.println("[QueryTest3] findUserByNameEquals : " + userRepository.findUserByNameEquals("martin"));
    }

    @Test
    void QueryTest4() {
        System.out.println("[QueryTest4] findTop1ByName : " + userRepository.findTop1ByName("martin"));
        System.out.println("[QueryTest4] findLast1ByName : " + userRepository.findLast1ByName("martin"));

        System.out.println("[QueryTest4] findTopByNameOrderByIdDesc : "
                + userRepository.findTopByNameOrderByIdDesc("martin"));
        System.out.println("[QueryTest4] findTopByNameOrderByIdDescEmailAsc : "
                + userRepository.findTopByNameOrderByIdDescEmailAsc("martin"));

        System.out.println("[QueryTest4] findFirstByNameOrderByIdDescEmailAsc : "
                + userRepository.findFirstByNameOrderByIdDescEmailAsc("martin"));
        System.out.println("[QueryTest4] findFirstByNameWithSortParams1 : "
                + userRepository.findFirstByName("martin", Sort.by(Sort.Order.desc("id"))));
        System.out.println("[QueryTest4] findFirstByNameWithSortParams2 : "
                + userRepository.findFirstByName("martin", getSort2()));
        System.out.println("[QueryTest4] findFirstByNameWithSortParams3 : "
                + userRepository.findFirstByName("martin", getSort3()));
    }

    private Sort getSort2() {
        Sort.Order id = Sort.Order.desc("id");
        Sort.Order email = Sort.Order.asc("email");

        return Sort.by(id, email);
    }

    private Sort getSort3() {
        Sort.Order id = Sort.Order.desc("id");
        Sort.Order email = Sort.Order.asc("email");
        Sort.Order createdAt = Sort.Order.by("createdAt");
        Sort.Order updatedAt = Sort.Order.asc("updatedAt");

        return Sort.by(id, email, createdAt, updatedAt);
    }

    @Test
    void QueryPagingTest() {
        System.out.println("[QueryPagingTest] findByNameWithPaging : "
                + userRepository.findByName("martin", getPageable()));

        System.out.println("[QueryPagingTest] findByNameWithPaging getContent(): "
                + userRepository.findByName("martin", getPageable()).getContent());

        System.out.println("[QueryPagingTest] findByNameWithPaging getTotalElements(): "
                + userRepository.findByName("martin", getPageable()).getTotalElements());
    }

    private Pageable getPageable() {
        Sort.Order id = Sort.Order.desc("id");

        return PageRequest.of(1, 1, Sort.by(id));
    }

    @Test
    void entityInsertAndUpdateTest() {
        User user1 = new User();
        user1.setName("martin");
        user1.setEmail("martin2@fastcmpus.com");
        userRepository.save(user1);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user2.setName("martin222");
        userRepository.save(user2);
    }

    @Test
    void entityEnumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);
        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));
    }

    @Test
    void listener1Test() {
        User user = new User();
        user.setEmail("martin2@fastcampis.com");
        user.setName("martin");
        userRepository.save(user);

        User user1 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user1.setName("marrrrtion");
        userRepository.save(user1);

        userRepository.deleteById(4L);
    }

    @Test
    void prePersistTest() {
        User user = new User();
        user.setEmail("martin2@fastcampis.com");
        user.setName("martin");
        userRepository.save(user);

        System.out.println(userRepository.findByEmail("martin2@fastcampis.com"));
    }

    @Test
    void preUpdateTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println("as-is : " + user);

        user.setName("martin22");
        userRepository.save(user);

        System.out.println("to-Be : " + userRepository.findAll().get(0));
    }
}