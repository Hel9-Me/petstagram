package com.petstagram.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * 전역 예외 처리 클래스.
 * 애플리케이션에서 발생하는 예외를 전역적으로 처리하여 응답을 반환합니다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ResponseStatusException 발생 시 처리하는 메서드.
     * @param ex 발생한 예외 객체
     * @return 예외에 맞는 상태 코드와 메시지를 담은 응답
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
        // 예외 발생 시 상태 코드와 메시지를 반환
        return ResponseEntity.status(ex.getStatusCode())
                .body(ex.getReason());
    }
}
