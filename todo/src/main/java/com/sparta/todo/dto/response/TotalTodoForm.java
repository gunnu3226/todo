package com.sparta.todo.dto.response;

import com.sparta.todo.entity.Todo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class TotalTodoForm {

    private String title;
    private Boolean finished;
    private LocalDateTime createTime;

    public TotalTodoForm(Todo todo) {
        this.title = todo.getTitle();
        this.finished = todo.getFinished();
        this.createTime = todo.getCreateTime();
    }
}
