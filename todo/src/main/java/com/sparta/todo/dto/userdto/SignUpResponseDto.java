package com.sparta.todo.dto.userdto;

import com.sparta.todo.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignUpResponseDto {

    private Long id;
    private String username;

    public SignUpResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUserName();
    }
}
