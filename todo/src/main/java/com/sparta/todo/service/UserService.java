package com.sparta.todo.service;

import com.sparta.todo.dto.userdto.SignUpResponseDto;
import com.sparta.todo.dto.userdto.SignupRequestDto;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.DuplicateUserNameException;
import com.sparta.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;


    @Transactional
    public SignUpResponseDto signUp(SignupRequestDto requestDto) {
        Optional<User> user = userRepository.findByUserName(requestDto.getUsername());
        if(user.isPresent()) {
            log.info("회원이름 중복");
            throw new DuplicateUserNameException("중복된 username 입니다.");
        }
        log.info("회원가입 정상로직 실행");
        User createdUser = userRepository.save(new User(requestDto.getUsername(), requestDto.getPassword()));
        return new SignUpResponseDto(createdUser);
    }
}
