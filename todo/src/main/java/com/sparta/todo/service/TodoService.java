package com.sparta.todo.service;

import com.sparta.todo.dto.request.TodoRequestDto;
import com.sparta.todo.dto.request.TodoUpdateRequestDto;
import com.sparta.todo.dto.response.TodoResponseDto;
import com.sparta.todo.dto.response.TotalTodoResponseDto;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public TodoResponseDto createTodo(TodoRequestDto requestDto, HttpServletRequest request) {
        log.info("투두 생성 서비스 집입");
        String userNameInToken = jwtUtil.validateTokenAndGetUserName(request);
        User user = userRepository.findByUserName(userNameInToken).orElseThrow();
        Todo savedTodo = todoRepository.save(new Todo(requestDto,user));

        return new TodoResponseDto(savedTodo,user);
    }


    public TodoResponseDto readTodo(Long todoId) {
        Todo findedTodo = todoRepository.findById(todoId)
                .orElseThrow(() -> new NoSuchElementException("해당 ID를 가지는 Todo를 찾을 수 없습니다."));
        return new TodoResponseDto(findedTodo);
    }

    public List<TotalTodoResponseDto> readTotalTodo() {
        log.info("투두전체조회 서비스");
        List<User> users = userRepository.findAll();
        log.info("회원조회 성공, 투두 리스트 반환");
        return users.stream()
                .map(TotalTodoResponseDto::new)
                .collect(Collectors.toList());
    }

    public TodoResponseDto updateTodo(Long todoId, TodoUpdateRequestDto requestDto, HttpServletRequest request) {
        log.info("투두 수정 서비스");
        String userNameInToken = jwtUtil.validateTokenAndGetUserName(request);
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new NoSuchElementException("해당 ID를 가지는 Todo를 찾을 수 없습니다.")
        );
        if(userNameInToken.equals(todo.getUser().getUserName())) {
            todo.update(requestDto);
            return new TodoResponseDto(todo);
        }
        throw new AccessDeniedException("작성자만 삭제/수정 할 수 있습니다.");
    }

    public TodoResponseDto finishTodo(Long todoId, HttpServletRequest request) {
        log.info("투두 완료 서비스");
        String userNameInToken = jwtUtil.validateTokenAndGetUserName(request);
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new NoSuchElementException("해당 ID를 가지는 Todo를 찾을 수 없습니다.")
        );
        if(userNameInToken.equals(todo.getUser().getUserName())) {
            todo.finish(todo);
            return new TodoResponseDto(todo);
        }
        throw new AccessDeniedException("작성자만 완료처리 할 수 있습니다.");
    }
}
