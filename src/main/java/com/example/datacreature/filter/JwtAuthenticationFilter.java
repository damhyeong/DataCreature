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

@Component // DI 주입
@RequiredArgsConstructor // lombok - 절대 NULL이여선 안되는 변수, 즉, final로 지정된 jwtProvider에 대해서 무조건적으로 생성자 변수를 받는다.
/** (OncePerRequestFilter)
 *  설명 :
 *  - 일회용 dispatcher, 그리고 Multi Thread를 지원한다.
 * */
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }

    // 뽑아낸 Token을 return해야하기에 String Return
    private String parseBearerToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");

        // null 이거나 0 길이거나 공백으로만 채워지면 false, 아니라면 true 반환
        boolean hasAuthorization = StringUtils.hasText(authorization);
        if(!hasAuthorization) return null;

        boolean isBearer = authorization.startsWith("Bearer "); // Bearer로 시작하느냐를 파악한다. => 아니라면 Bearer Token이 아니다.
        if(!isBearer) return null;

        // 여기까지 왔으면 Bearer 토큰 방식이라는 것을 증명한다고 한다.

        String token = authorization.substring(7);
        return token;
    }
}
