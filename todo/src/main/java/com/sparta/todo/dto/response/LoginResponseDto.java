package com.sparta.todo.dto.response;

import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginResponseDto {

    private User user;
    private String token;

    public LoginResponseDto(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
