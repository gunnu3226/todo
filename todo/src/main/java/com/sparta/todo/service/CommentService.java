package com.sparta.todo.service;

import com.sparta.todo.dto.request.CommentRequestDto;
import com.sparta.todo.dto.response.CommentResponseDto;
import com.sparta.todo.entity.Comment;
import com.sparta.todo.entity.Todo;
import com.sparta.todo.entity.User;
import com.sparta.todo.exception.NoUserException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.CommentRepository;
import com.sparta.todo.repository.TodoRepository;
import com.sparta.todo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CommentService {

    private final CommentRepository commentRepository;
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public CommentResponseDto createComment(Long todoId, CommentRequestDto requestDto, HttpServletRequest request) {
        String commenter = jwtUtil.validateTokenAndGetUserName(request);
        Todo todo = todoRepository.findById(todoId).orElseThrow(
                () -> new NoSuchElementException("해당 투두가 존재하지 않습니다.")
        );
        User user = userRepository.findByUserName(commenter).orElseThrow(
                () -> new NoUserException("댓글 작성자의 회원정보를 찾을 수 없습니다.")
        );
        Comment savedComment = commentRepository.save(new Comment(requestDto, todo, user));
        return new CommentResponseDto(savedComment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentRequestDto requestDto, HttpServletRequest request) {
        String requestUserName = jwtUtil.validateTokenAndGetUserName(request);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("해당 댓글은 존재하지 않습니다.")
        );
        if(requestUserName.equals(comment.getUser().getUserName())) {
            comment.update(requestDto);
            return new CommentResponseDto(comment);
        }
        throw new AccessDeniedException("작성자만 수정할 수 있습니다.");
    }

    @Transactional
    public Long deleteComment(Long commentId, HttpServletRequest request) {
        String requestUserName = jwtUtil.validateTokenAndGetUserName(request);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new NoSuchElementException("해당 댓글은 존재하지 않습니다.")
        );
        if(requestUserName.equals(comment.getUser().getUserName())) {
            commentRepository.deleteById(commentId);
            return commentId;
        }
        throw new AccessDeniedException("작성자만 삭제할 수 있습니다.");
    }
}
