package com.sparta.todo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LoginRequestDto {
    private String userName;
    private String password;
}
