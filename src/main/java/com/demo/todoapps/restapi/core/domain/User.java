package com.demo.todoapps.restapi.core.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import org.springframework.data.annotation.Id;


@Getter
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;
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
