package com.petstagram.service;

import com.petstagram.dto.user.ProfileResponseDto;

/**
 * 사용자 관련 서비스 인터페이스.
 * 사용자 프로필 조회 기능을 정의합니다.
 */
public interface UserService {

    /**
     * 사용자의 프로필을 조회하는 메서드.
     * @param id 조회할 사용자의 ID
     * @return 사용자 프로필 정보 DTO
     */
    ProfileResponseDto getProfile(Long id);
}
