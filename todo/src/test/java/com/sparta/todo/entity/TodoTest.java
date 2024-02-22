package com.sparta.todo.entity;

import com.sparta.todo.dto.request.TodoRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodoTest {

    @Test
    void update() {
        //given
        User user = new User();
        TodoRequestDto requestDto = new TodoRequestDto();
        requestDto.setTitle("테스트 제목");
        requestDto.setContents("테스트 내용");
        Todo todo = new Todo(requestDto, user);
        TodoUpdateRequestDto updateDto = new TodoUpdateRequestDto();
        updateDto.setTitle("변경된 제목");
        updateDto.setContents("변경된 내용");

        //when
        todo.update(updateDto);

        //then
        assertEquals(updateDto.getTitle(), todo.getTitle());
        assertEquals(updateDto.getContents(), todo.getContents());
    }

    @Test
    void finish() {
        //given
        Todo todo = new Todo();
        //when
        todo.finish();
        //then
        assertEquals(true, todo.getFinished());
    }
}