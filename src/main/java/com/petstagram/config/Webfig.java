package com.petstagram.config;

import jakarta.servlet.Filter;
import com.petstagram.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Webfig 클래스는 Spring Boot 애플리케이션의 필터를 설정하는 클래스입니다.
 *
 * 이 클래스는 `@Configuration` 어노테이션을 사용하여 Spring의 설정 클래스로 등록되며,
 * 로그인 필터를 애플리케이션에 등록하고, 이를 통해 모든 요청에 대해 특정 필터링 작업을 수행하도록 합니다.
 */
@Configuration
public class Webfig {

    /**
     * 로그인 필터를 등록하는 메서드입니다.
     *
     * 로그인 필터는 HTTP 요청을 가로채서 로그인 상태를 확인하고,
     * 인증되지 않은 사용자가 접근할 수 없는 URL에 대해서 차단하는 역할을 합니다.
     *
     * @return 필터 등록 설정을 반환합니다.
     */
    @Bean
    public FilterRegistrationBean loginFilter() {
        // FilterRegistrationBean은 Spring에서 필터를 등록하기 위한 클래스입니다.
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();

        // 필터 객체를 생성하여 필터에 등록합니다.
        filterRegistrationBean.setFilter(new LoginFilter());

        // 필터의 실행 순서를 설정합니다. 숫자가 낮을수록 우선순위가 높습니다.
        filterRegistrationBean.setOrder(1);

        // 필터가 적용될 URL 패턴을 설정합니다.
        // 여기서는 모든 URL(`/*`)에 대해 필터를 적용하도록 설정합니다.
        filterRegistrationBean.addUrlPatterns("/*");

        // 필터 등록 정보를 반환합니다.
        return filterRegistrationBean;
    }
}
