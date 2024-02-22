package com.sparta.todo.entity;

import com.sparta.todo.dto.request.CommentRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CommentTest {

    @Test
    void update() {
        //given
        User user = new User();
        Todo todo = new Todo();
        CommentRequestDto requestDto = new CommentRequestDto();
        requestDto.setComment("테스트 코멘트");
        Comment comment = new Comment(requestDto, todo, user);

        CommentRequestDto changeRequestDto = new CommentRequestDto();
        changeRequestDto.setComment("변경된 코멘트");

        //when
        comment.update(changeRequestDto);

        //then
        assertEquals("변경된 코멘트", comment.getComment());
    }
}