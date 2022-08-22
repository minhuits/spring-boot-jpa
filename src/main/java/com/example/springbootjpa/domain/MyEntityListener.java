package com.example.springbootjpa.domain;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class MyEntityListener {

    @PrePersist
    public void prePersist(Object object) {
        if (object instanceof Auditable) {
            System.out.println(">>> PrePersist");
            ((Auditable) object).setCreatedAt(LocalDateTime.now());
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Object object) {
        if (object instanceof Auditable) {
            System.out.println(">>> PreUpdate");
            ((Auditable) object).setUpdatedAt(LocalDateTime.now());
        }
    }
}
