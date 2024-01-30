package com.sparta.todo.entity;

import com.sparta.todo.dto.request.CommentRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo todo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentRequestDto requestDto, Todo todo, User user) {
        this.comment = requestDto.getComment();
        this.todo = todo;
        this.user = user;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
