package com.sparta.todo.service;

import com.sparta.todo.dto.request.SignupRequestDto;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @Nested
    class signUp {

        @Test
        @DisplayName("회원가입 성공")
        void signUpSuccess() {
            //given
            SignupRequestDto requestDto = new SignupRequestDto();
            requestDto.setUserName("testuser");
            requestDto.setPassword("testpassword");

            //when
            

            //then
        }
    }
}