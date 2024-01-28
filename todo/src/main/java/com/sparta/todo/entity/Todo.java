package com.sparta.todo.entity;

import jakarta.persistence.*;
import org.springframework.data.util.Lazy;

import java.time.LocalDateTime;

@Entity
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private boolean finished;

    private LocalDateTime localDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
