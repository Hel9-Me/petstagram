package com.petstagram.common.constants;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FriendErrorCode implements ErrorCode {

    NOT_FOUND_INVITED(HttpStatus.NOT_FOUND,"친구 신청을 찾을 수 없습니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND,"친구를 찾을 수 없습니다" );

    private final HttpStatus httpStatus;
    private final String message;
}
