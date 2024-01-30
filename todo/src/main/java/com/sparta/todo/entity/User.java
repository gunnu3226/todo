package com.sparta.todo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos = new ArrayList<>();

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }
}
