package com.petstagram.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;
import java.io.IOException;

/**
 * 로그인 필터 클래스.
 * 사용자가 인증되지 않은 상태에서 접근할 수 없는 URL을 차단하는 필터입니다.
 * 화이트리스트에 등록된 URL은 인증 없이 접근을 허용합니다.
 */
@Slf4j
public class LoginFilter implements Filter {

    // 화이트리스트 URL 배열 (인증 없이 접근 허용)
    private static final String[] WHITE_LIST = {"/signup", "/login"};

    /**
     * HTTP 요청을 가로채어 로그인 상태를 검사하는 메소드.
     * 화이트리스트에 없는 요청에 대해서는 세션에 'USER_ID'가 있는지 확인하여 인증되지 않은 사용자의 접근을 막습니다.
     *
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @param chain 필터 체인 객체
     * @throws IOException 입출력 예외 발생 시
     * @throws ServletException 서블릿 관련 예외 발생 시
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 요청 URI

        log.info("로그인 필터 로직 실행");

        // 화이트리스트에 포함되지 않은 URI에 대해서만 필터 적용
        if (!isWhiteList(requestURI)) {
            HttpSession session = httpRequest.getSession(false);

            // 세션이 없거나 'USER_ID' 속성이 없는 경우, 인증되지 않은 사용자로 간주
            if (session == null || session.getAttribute("USER_ID") == null) {

                log.warn("미인증 사용자 요청: {}", requestURI);
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                httpResponse.setContentType("application/json");
                httpResponse.setCharacterEncoding("UTF-8");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                String responseMessage = "로그인 후 시도 해주세요."; // 인증되지 않은 사용자에게 메시지 반환
                httpResponse.getWriter().write(responseMessage);
                return; // 요청 중단
            }
            log.info("로그인된 사용자 요청: {}", requestURI);
        }
        chain.doFilter(request, response); // 인증된 사용자 또는 화이트리스트 요청은 필터 체인을 이어서 진행
    }

    /**
     * 화이트리스트 URL인지 확인하는 메소드.
     *
     * @param requestURI 요청 URI
     * @return 화이트리스트에 포함된 URI일 경우 true, 그렇지 않으면 false
     */
    private boolean isWhiteList(String requestURI) {
        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
    }
}
