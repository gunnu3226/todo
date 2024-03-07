package com.sparta.todo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.sparta.todo.dto.request.LoginRequestDto;
import com.sparta.todo.dto.request.SignupRequestDto;
import com.sparta.todo.dto.response.LoginResponseDto;
import com.sparta.todo.dto.response.UserResponseDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

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
            User testUser = new User("testuser", passwordEncoder.encode("testpassword"));

            given(userRepository.findByUserName(requestDto.getUserName())).willReturn(Optional.empty());
            given(userRepository.save(any(User.class))).willReturn(testUser);

            //when
            UserResponseDto userResponseDto = userService.signUp(requestDto);

            //then
            assertThat(userResponseDto.getUsername()).isEqualTo("testuser");
        }

        @Test
        @DisplayName("로그인 성공")
        void login() {
            //given
            LoginRequestDto requestDto = new LoginRequestDto();
            requestDto.setUserName("testuser");
            requestDto.setPassword("testpassword");

            User user = new User("testuser","testpassword");
            given(userRepository.findByUserName("testuser")).willReturn(Optional.of(user));
            given(passwordEncoder.matches(requestDto.getPassword(),user.getPassword())).willReturn(true);
            given(jwtUtil.createToken(user.getUserName())).willReturn("token");

            //when
            LoginResponseDto response = userService.login(requestDto);

            //then
            assertThat(response.getUser()).isEqualTo(user);
            assertThat(response.getToken()).isEqualTo("token");
        }
    }
}