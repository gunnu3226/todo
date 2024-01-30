package com.sparta.todo.service;

import com.sparta.todo.dto.request.LoginRequestDto;
import com.sparta.todo.dto.response.LoginResponseDto;
import com.sparta.todo.dto.response.UserResponseDto;
import com.sparta.todo.dto.request.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.DuplicateUserNameException;
import com.sparta.todo.exception.IllegalLoginException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDto signUp(SignupRequestDto requestDto) {
        log.info("회원가입 서비스 호출");
        Optional<User> user = userRepository.findByUserName(requestDto.getUserName());
        if(user.isPresent()) {
            log.info("회원이름 중복");
            throw new DuplicateUserNameException("중복된 username 입니다.");
        }
        log.info("회원가입 정상로직 실행");
        User createdUser = userRepository.save(new User(requestDto.getUserName(),
                passwordEncoder.encode(requestDto.getPassword())));
        return new UserResponseDto(createdUser);
    }

    public LoginResponseDto login(LoginRequestDto requestDto) {
        log.info("로그인 서비스 호출");
        String userName = requestDto.getUserName();
        String password = requestDto.getPassword();

        User user = userRepository.findByUserName(userName).orElseThrow(
                () -> new IllegalLoginException("회원을 찾을 수 없습니다.")
        );

        if(!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalLoginException("회원을 찾을 수 없습니다.");
        }
        log.info("아이디 비밀번호 일치. 토큰 생성");
        String token = jwtUtil.createToken(user.getUserName());
        return new LoginResponseDto(user, token);
    }
}
