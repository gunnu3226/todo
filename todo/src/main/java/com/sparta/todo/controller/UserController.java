package com.sparta.todo.controller;

import com.sparta.todo.dto.request.LoginRequestDto;
import com.sparta.todo.dto.request.SignupRequestDto;
import com.sparta.todo.dto.response.LoginResponseDto;
import com.sparta.todo.dto.response.UserResponseDto;
import com.sparta.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Validated @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        log.info("회원가입 컨트롤러 호출");
        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            ResponseBodyForm responseForm = new ResponseBodyForm(HttpStatus.BAD_REQUEST, createErrorMessages(bindingResult).toString(), requestDto);
            return ResponseEntity.badRequest()
                    .body(responseForm);
        }
        ResponseBodyForm responseBodyForm = new ResponseBodyForm(HttpStatus.CREATED, "회원가입 성공", userService.signUp(requestDto));
        log.info("회원가입 성공");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBodyForm);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseBodyForm> login(@RequestBody LoginRequestDto requestDto) {
        log.info("로그인 컨트롤러 호출");
        LoginResponseDto responseDto = userService.login(requestDto);
        ResponseBodyForm responseForm = new ResponseBodyForm(HttpStatus.OK, "로그인 성공", new UserResponseDto(responseDto.getUser()));
        log.info("로그인 성공");
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, responseDto.getToken())
                .body(responseForm);
    }

    private List<String> createErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}