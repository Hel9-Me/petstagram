package com.petstagram.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommentErrorCode implements ErrorCode {
    INVALID_ACCESS(HttpStatus.UNAUTHORIZED,"댓글 변경 권한이 없습니다"),
    NOT_FOUND(HttpStatus.NOT_FOUND,"댓글을 찾을 수 없습니다")
    ;


    private final HttpStatus httpStatus;
    private final String message;
    }
