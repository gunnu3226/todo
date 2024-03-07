package com.sparta.todo.service;

import static org.junit.jupiter.api.Assertions.*;

import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @InjectMocks
    TodoService todoService;

    @Mock
    UserRepository userRepository;

    @Mock
    TodoRepository todoRepository;

    @Mock
    JwtUtil jwtUtil;

    @Test
    @DisplayName("투두생성")
    void createTodo() {
    }
}