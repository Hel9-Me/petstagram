package com.petstagram.controller;

import com.petstagram.dto.user.ProfileResponseDto;
import com.petstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * UserController는 사용자 관련 API 엔드포인트를 제공합니다.
 * 사용자의 프로필 정보를 조회하는 기능을 처리합니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    // UserService를 주입받아 사용자 관련 비즈니스 로직을 처리
    private final UserService userService;

    /**
     * 특정 사용자의 프로필 정보를 조회하는 API 엔드포인트입니다.
     *
     * @param id 조회하려는 사용자의 ID
     * @return 사용자 프로필 정보를 담은 응답 DTO와 HTTP 200 상태 코드
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long id) {

        // 서비스에서 사용자 프로필 정보 조회
        ProfileResponseDto responseDto = userService.getProfile(id);

        // 조회된 프로필 정보를 반환하며, HTTP 200 상태 코드 반환
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
