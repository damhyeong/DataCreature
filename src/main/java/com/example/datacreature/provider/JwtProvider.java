package com.example.datacreature.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// JWT 발급 및 확인기
@Component
public class JwtProvider {
    private String secretKey = "S3cr3tK3y";

    public String create(String email){
        // 1시간짜리 만료기간 만들기
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // jwt 생성
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                .compact();

        return jwt;
    }

    public String validate(String jwt){
        Claims claims = null;

        try {
            claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwt).getBody();
        }catch (Exception exception){
            exception.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }
}
