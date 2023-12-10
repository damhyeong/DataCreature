package com.example.datacreature.filter;

import com.example.datacreature.provider.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/** (OncePerRequestFilter)
 *  설명 :
 *  - 일회용 dispatcher, 그리고 Multi Thread를 지원한다.
 * */
@Component // DI 주입
@RequiredArgsConstructor // lombok - 절대 NULL이여선 안되는 변수, 즉, final로 지정된 jwtProvider에 대해서 무조건적으로 생성자 변수를 받는다.
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String token = parseBearerToken(request); // token이 null 혹은 맞지 않는 형식인지 검사.
            System.out.println("token : " + token);
            if(token == null){
                filterChain.doFilter(request, response); // token이 없다면, 걸러낸다.
                return;
            }

            // JWT 검증 과정에서 오류가 난다면, JwtProvider 메서드인 validate가 null return 한다.
            String email = jwtProvider.validate(token);
            System.out.println("email : " + email);

            if(email == null){ // 검증 과정에서 오류가 났다면 걸러낸다.
                filterChain.doFilter(request, response);
                return;
            }

            // email : 사용자의 아이디, password : 비밀번호 -> 사용하지 않음.
            AbstractAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, null, AuthorityUtils.NO_AUTHORITIES);
            // WebAuthenticationDetailSource : 웹인증 사후정보 소스
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authenticationToken);

            SecurityContextHolder.setContext(securityContext);
        } catch (Exception exception){
            exception.printStackTrace();
        }

        filterChain.doFilter(request, response);
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
