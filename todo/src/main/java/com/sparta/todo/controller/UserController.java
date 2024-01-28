package com.sparta.todo.controller;

import com.sparta.todo.dto.userdto.SignupRequestDto;
import com.sparta.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<?> signUp(@Validated @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        log.info("회원가입 컨트롤러 호출");
        if(bindingResult.hasErrors()) {
            log.info("검증 오류 발생 errors={}", bindingResult);
            ResponseBody responseBody = new ResponseBody(HttpStatus.BAD_REQUEST, createErrorMessages(bindingResult).toString(), requestDto);
            return ResponseEntity.badRequest()
                    .body(responseBody);
        }
        ResponseBody responseBody = new ResponseBody(HttpStatus.CREATED, "회원가입 성공", userService.signUp(requestDto));
        log.info("회원가입 성공");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(responseBody);
    }

    private List<String> createErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }
}