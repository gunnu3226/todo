package com.sparta.todo.controller;

import com.sparta.todo.dto.request.TodoRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import com.sparta.todo.dto.response.TodoResponseDto;
import com.sparta.todo.dto.response.TotalTodoResponseDto;
import com.sparta.todo.service.TodoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping("/new")
    public ResponseEntity<ResponseBodyForm> createTodo(
            @RequestBody TodoRequestDto requestDto,
            HttpServletRequest request
    ) {
        log.info("투두생성 컨트롤러 진입");
        TodoResponseDto responseDto = todoService.createTodo(requestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, String.format("/todo/%d",responseDto.getId()))
                .body(new ResponseBodyForm(HttpStatus.CREATED, "Todo 작성 성공", responseDto));
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<ResponseBodyForm> readTodo(
            @PathVariable Long todoId
    ) {
        log.info("투두 개별조회 컨트롤러");
        TodoResponseDto responseDto = todoService.readTodo(todoId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "Todo 조회 성공", responseDto));
    }

    @GetMapping("/total")
    public ResponseEntity<ResponseBodyForm> readTotalTodo() {
        log.info("투두 전체조회 컨트롤러");
        List<TotalTodoResponseDto> totalTodoResponseDto = todoService.readTotalTodo();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "ToTal Todo 조회 성공", totalTodoResponseDto));
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<ResponseBodyForm> updateTodo(
            @PathVariable Long todoId,
            @RequestBody TodoUpdateRequestDto requestDto,
            HttpServletRequest request) {
        log.info("투두 업데이트 컨트롤러");
        TodoResponseDto responseDto = todoService.updateTodo(todoId, requestDto, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "Todo 수정 성공", responseDto));
    }

    @PatchMapping("/{todoId}/finish")
    public ResponseEntity<ResponseBodyForm> finishTodo(@PathVariable Long todoId, HttpServletRequest request) {
        log.info("투두 완료 기능 컨트롤러");
        TodoResponseDto responseDto = todoService.finishTodo(todoId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "Todo 완료처리 성공", responseDto));
    }
}


