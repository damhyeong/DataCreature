package com.example.datacreature.filter;

import com.example.datacreature.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor // lombok
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        // null 이거나 0 길이거나 공백으로만 채워지면 false, 아니라면 true 반환
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer ");
        if(!isBearer) return null;

        // 여기까지 왔으면 Bearer 토큰 방식이라는 것을 증명한다고 한다.

        String token = authorization.substring(7);
        return token;
    }
}
