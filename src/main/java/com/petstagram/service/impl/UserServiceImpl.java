package com.petstagram.service.impl;

import com.petstagram.dto.user.ProfileResponseDto;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 사용자 프로필 관련 서비스 구현 클래스.
 * 사용자의 프로필 정보를 조회하는 기능을 제공합니다.
 */
@Service
@RequiredArgsConstructor  // 생성자 주입을 위해 Lombok 어노테이션 사용
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;  // User 엔티티 관련 데이터 처리

    /**
     * 사용자 프로필 조회 메서드.
     * 주어진 사용자 ID에 해당하는 프로필 정보를 반환합니다.
     *
     * @param id 프로필을 조회할 사용자 ID
     * @return 해당 사용자의 프로필 정보를 담은 DTO
     */
    public ProfileResponseDto getProfile(Long id) {
        // 사용자 ID로 해당 사용자 조회
        User findUser = userRepository.findByIdOrElseThrows(id);

        // 프로필 정보 반환
        return new ProfileResponseDto(findUser);
    }
}
