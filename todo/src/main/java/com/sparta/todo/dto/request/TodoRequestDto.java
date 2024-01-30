package com.sparta.todo.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoRequestDto {

    @NotNull(message = "제목 입력은 필수입니다.")
    private String title;

    private String contents;
}
