package com.example.datacreature.controller;

import com.example.datacreature.dto.request.LoginRequest;
import com.example.datacreature.dto.response.AuthResponse;
import com.example.datacreature.provider.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(JwtProvider jwtProvider){
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(LoginRequest loginRequest){
        // 사용자 맞는지 인증 로직 구현 - 사용자 이메일 비밀번호 유효 확인

        //인증 성공 시 JWT 토큰 생성
        String token = jwtProvider.create(loginRequest.getEmail());
        System.out.println("/login .....");
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
