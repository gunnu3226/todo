package com.sparta.todo.dto.response;

import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TodoResponseDto {
    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createTime;

    private UserResponseDto writer;

    public TodoResponseDto(Todo todo, User user) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createTime = todo.getCreateTime();
        this.writer = new UserResponseDto(user);
    }

    public TodoResponseDto(Todo todo) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.createTime = todo.getCreateTime();
        this.writer = new UserResponseDto(todo.getUser());
    }
}
