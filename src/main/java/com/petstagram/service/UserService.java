package com.petstagram.service;

import com.petstagram.dto.user.ProfileResponseDto;

public interface UserService {
    ProfileResponseDto getProfile(Long id);
    ProfileResponseDto updateProfile(Long id, String newName, String password, String newPassword);
}
