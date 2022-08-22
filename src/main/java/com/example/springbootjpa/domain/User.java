package com.example.springbootjpa.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        name = "users",
        indexes = {@Index(columnList = "name")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})}
)
@Entity
@EntityListeners(value = MyEntityListener.class)
public class User implements Auditable{
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>> PostPersist");
//    }
//
//    @PostUpdate
//    public void postUpdate() {
//        System.out.println(">>> PostUpdate");
//    }
//
//    @PreRemove
//    public void preRemove() {
//        System.out.println(">>> PreRemove");
//    }
//
//    @PostRemove
//    public void postRemove() {
//        System.out.println(">>> PostRemove");
//    }
//
//    @PostLoad
//    public void postLoad() {
//        System.out.println(">>> PostLoad");
//    }

}