package com.petstagram.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum BoardErrorCode implements ErrorCode {

    INVALID_ACCESS(HttpStatus.UNAUTHORIZED,"게시물 변경 권한이 없습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"게시물을 찾을 수 없습니다")
    ;


    private final HttpStatus httpStatus;
    private final String message;
    }
