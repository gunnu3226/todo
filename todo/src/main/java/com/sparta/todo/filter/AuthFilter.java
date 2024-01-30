package com.sparta.todo.filter;

import com.sparta.todo.entity.User;
import com.sparta.todo.exception.IllegalLoginException;
import com.sparta.todo.jwt.JwtUtil;
import com.sparta.todo.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
//@Component
@Order(2)
@RequiredArgsConstructor
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                url.startsWith("api/user") || url.startsWith("/css") || url.startsWith("/js")
        ) {
            chain.doFilter(request, response);
        } else {
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) {
                String token = jwtUtil.substringToken(tokenValue);

                if(!jwtUtil.validateToken(token)) {
                    throw new IllegalArgumentException("Token Error");
                }

                Claims info = jwtUtil.getUserInfoFromToken(token);

                User user = userRepository.findByUserName(info.getSubject()).orElseThrow(() ->
                        new IllegalLoginException("일치하는 유저가 없습니다."));

                request.setAttribute("user", user);
                chain.doFilter(request, response);
            } else {
                throw new IllegalArgumentException("Not Found Token");
            }
        }
    }
}
