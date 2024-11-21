package com.petstagram.service;

import com.petstagram.model.entity.User;

/**
 * 인증 관련 서비스 인터페이스.
 * 회원 가입, 로그인, 탈퇴와 관련된 기능을 정의합니다.
 */
public interface AuthService {

    /**
     * 사용자를 회원 가입시키는 메서드.
     * @param name 사용자의 이름
     * @param email 사용자의 이메일 (고유해야 함)
     * @param password 사용자의 비밀번호
     */
    void singUp(String name, String email, String password);

    /**
     * 사용자가 입력한 이메일과 비밀번호로 로그인하는 메서드.
     * @param email 사용자의 이메일
     * @param password 사용자의 비밀번호
     * @return 로그인 성공 시 사용자 객체 반환
     */
    User login(String email, String password);

    /**
     * 사용자의 계정을 탈퇴시키는 메서드.
     * @param userId 탈퇴할 사용자 ID
     */
    void leave(Long userId);
}
