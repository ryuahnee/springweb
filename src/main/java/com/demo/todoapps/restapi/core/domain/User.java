package com.demo.todoapps.restapi.core.domain;


import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String email;

    public User() {
    }

    public User update(String name, String email) {
        this.name = name;
        this.email = email;
        return this;
    }

}
