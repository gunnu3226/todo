package com.sparta.todo.dto.response;

import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInResponseDto {

    private User user;
    private String token;


    public SignInResponseDto(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
