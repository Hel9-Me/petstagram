package com.petstagram.service;

import com.petstagram.dto.ProfileResponseDto;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Userservice {

    private final UserRepository userRepository;

    public ProfileResponseDto getProfile(Long id) {
        User finduser = userRepository.findByIdOrElseThrows(id);
        return new ProfileResponseDto(finduser);
    }

}
