package com.sparta.todo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInRequestDto {
    private String userName;
    private String password;
}
