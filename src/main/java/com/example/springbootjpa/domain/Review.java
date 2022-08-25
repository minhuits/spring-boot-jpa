package com.example.springbootjpa.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Review extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    private float score;

    @ManyToOne
    private User user;

    @ManyToOne

    private Book book;
}
