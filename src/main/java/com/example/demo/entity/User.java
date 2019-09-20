package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
public class User {

    @Id
    Long id;

    @Column
    String name;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
