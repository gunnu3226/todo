package com.sparta.todo.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoUpdateRequestDto {

    private String title;

    private String contents;
}
