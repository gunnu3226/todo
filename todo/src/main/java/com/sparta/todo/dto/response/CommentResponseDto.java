package com.sparta.todo.dto.response;

import com.sparta.todo.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResponseDto {

    private Long commentId;

    private String comment;

    private String commenter;

    private Long todoId;

    private String todoTitle;

    public CommentResponseDto(Comment comment) {
        this.commenter = comment.getUser().getUserName();
        this.commentId = comment.getId();
        this.comment = comment.getComment();
        this.todoId = comment.getTodo().getId();
        this.todoTitle = comment.getTodo().getTitle();
    }
}
