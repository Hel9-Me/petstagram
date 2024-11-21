package com.petstagram.service.impl;

import com.petstagram.dto.user.ProfileResponseDto;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public ProfileResponseDto getProfile(Long id) {
        User finduser = userRepository.findByIdOrElseThrows(id);
        return new ProfileResponseDto(finduser);
    }

}
