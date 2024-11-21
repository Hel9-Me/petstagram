package com.petstagram.model.entity.enums;

/**
 * 계정 상태를 나타내는 열거형.
 * - 계정이 활성화(Y) 또는 비활성화(N) 상태임을 나타냄.
 */
public enum AccountStatus {
    Y("계정 활성화"),  // 계정 활성화 상태
    N("계정 비활성화 ");  // 계정 비활성화 상태

    private final String message;

    /**
     * 열거형 생성자.
     *
     * @param message 계정 상태 메시지
     */
    AccountStatus(String message){
        this.message = message;
    }

    /**
     * 상태에 대한 설명을 반환합니다.
     *
     * @return 상태 메시지
     */
    public String getMessage() {
        return message;
    }
}
