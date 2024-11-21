package com.petstagram.dto.user;

import com.petstagram.model.entity.User;
import lombok.Getter;

import java.sql.Timestamp;

/**
 * 사용자 프로필 조회 응답 DTO 클래스.
 * 특정 사용자의 프로필 정보(이름, 이메일, 가입 시간 등)를 반환하는데 사용됩니다.
 */
@Getter
public class ProfileResponseDto {

    private Long id; // 사용자 ID
    private String name; // 사용자 이름
    private String email; // 사용자 이메일
    private Timestamp createdAt; // 사용자 가입 시간

    /**
     * User 엔티티 객체를 사용하여 ProfileResponseDto를 생성합니다.
     *
     * @param user 사용자 엔티티 객체
     */
    public ProfileResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
