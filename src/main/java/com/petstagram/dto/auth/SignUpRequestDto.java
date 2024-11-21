package com.petstagram.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * 회원 가입 요청을 위한 DTO 클래스.
 * 사용자의 이름, 이메일, 비밀번호를 받아 회원 가입 처리를 합니다.
 */
@Getter
public class SignUpRequestDto {

    /**
     * 사용자 이름. 비어 있을 수 없으며, 이름 유효성 검사를 위한 메시지를 포함.
     *
     * @NotBlank 빈 값일 수 없도록 검증
     */
    @NotBlank(message = "올바른 이름을 입력해주세요")
    private final String name;

    /**
     * 이메일 주소. 이메일 형식에 맞는지 검사하는 정규 표현식 적용.
     *
     * @pattern 정규식 패턴: 이메일 형식을 확인하는 정규식
     * @message "올바른 이메일 형식을 입력해 주세요" 유효성 검사 실패 시 메시지
     */
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "올바른 이메일 형식을 입력해 주세요")
    private final String email;

    /**
     * 비밀번호. 빈 값일 수 없으며, 추후 패턴 검사가 필요할 수 있음.
     *
     * @NotBlank 비밀번호가 비어 있지 않도록 검증
     */
    @NotBlank
    private final String password;

    /**
     * 생성자
     *
     * @param name 사용자 이름
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     */
    public SignUpRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
