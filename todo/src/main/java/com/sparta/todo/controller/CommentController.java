package com.sparta.todo.controller;

import com.sparta.todo.dto.request.CommentRequestDto;
import com.sparta.todo.dto.response.CommentResponseDto;
import com.sparta.todo.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/{todoId}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/new")
    public ResponseEntity<ResponseBodyForm> createComment(@PathVariable Long todoId,
                                                          @RequestBody CommentRequestDto requestDto,
                                                          HttpServletRequest request) {
        CommentResponseDto responseDto = commentService.createComment(todoId, requestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseBodyForm(HttpStatus.CREATED, "댓굴 작성 성공", responseDto));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<ResponseBodyForm> updateComment(@PathVariable Long commentId,
                                                          @RequestBody CommentRequestDto requestDto,
                                                          HttpServletRequest request) {
        CommentResponseDto responseDto = commentService.updateComment(commentId, requestDto, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "댓글 수정 성공", responseDto));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseBodyForm> deleteComment(@PathVariable Long commentId, HttpServletRequest request) {
        Long deletedCommentId = commentService.deleteComment(commentId, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseBodyForm(HttpStatus.OK, "댓글 삭제 성공", "deletedCommentId :" + deletedCommentId));
    }
}
