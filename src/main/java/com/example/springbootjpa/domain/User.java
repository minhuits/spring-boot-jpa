package com.example.springbootjpa.domain;

import com.example.springbootjpa.domain.listener.UserEntityListener;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Table(name = "users", indexes = {@Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
@Entity
@EntityListeners(value = UserEntityListener.class)
public class User extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;
}