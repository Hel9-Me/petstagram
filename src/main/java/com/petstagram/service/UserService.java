package com.petstagram.service;

import com.petstagram.dto.user.ProfileResponseDto;

public interface UserService {
    ProfileResponseDto getProfile(Long id);
}
