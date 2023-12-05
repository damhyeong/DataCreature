package com.example.datacreature.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

// JWT 발급 및 확인기

@Component // 제어 역전을 통한 의존성 주입 - DI
public class JwtProvider {

    @Value("${secret-key}") // application.properties에서 secret-key 변수를 가져온다.
    private String secretKey; // secretKey가 유튜브에 써 있기에, 임의로 다른 내용 지정.

    public String create(String email){
        // 1시간짜리 만료기간 만들기
        /** Instant는 일회용 time-line 객체이다. */
        Date expiredDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));

        // jwt 생성
        try{
            String jwt = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, secretKey.getBytes())
                    .setSubject(email).setIssuedAt(new Date()).setExpiration(expiredDate)
                    .compact();
            return jwt;
        } catch (Exception e){
            System.out.println("jwt Error start : ");
            e.printStackTrace();
            return null;
        }
    }

    public String validate(String jwt){
        // JWT JSON Map 변환을 위한 객체 - Claims
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
