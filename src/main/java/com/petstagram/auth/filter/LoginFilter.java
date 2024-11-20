package com.petstagram.auth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;

@Slf4j
public class LoginFilter implements Filter {

    private static final String[] WHITE_LIST = {"/signup", "/login"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI  = httpRequest.getRequestURI();

        log.info("로그인 필터 로직 실행");

        if(!isWhiteList(requestURI)){
            HttpSession session = httpRequest.getSession(false);

            if (session == null || session.getAttribute("USER_ID") == null) {

                log.warn("미인증 사용자 요청: {}", requestURI);
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setContentType("application/json; charset=UTF-8");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                String responseMessage = "{\"message\": \"로그인이 필요합니다\"}";
                httpResponse.getWriter().write(responseMessage);
                httpResponse.getWriter().flush();  // 클라이언트로 바로 전송
                return; // 요청 중단
            }
            log.info("로그인된 사용자 요청: {}", requestURI);
        }
        chain.doFilter(request, response); // 다음 필터로 진행 ( 필터 없을 시 컨트롤러 호출 )
    }

    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
