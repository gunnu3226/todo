package com.sparta.todo.dto.response;

import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponseDto {

    private Long id;
    private String username;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
    }
}
