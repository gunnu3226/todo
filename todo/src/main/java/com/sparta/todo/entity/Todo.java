package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.util.Lazy;

import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String contents;

    private Boolean finished;

    @CreationTimestamp
    private LocalDateTime createTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Todo(TodoRequestDto requestDto, User user) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.finished = false;
        this.user = user;
    }

    public void update(TodoUpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
