package com.sparta.todo.dto.response;

import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class TotalTodoResponseDto {

    private UserResponseDto writer;

    private List<TotalTodoForm> todoList;

    public TotalTodoResponseDto(User user) {
        this.writer = new UserResponseDto(user);
        this.todoList = user.getTodos().stream()
                .map(TotalTodoForm::new)
                .toList();
    }
}
