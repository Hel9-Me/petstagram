package com.petstagram.auth.service;

import com.petstagram.auth.dto.SignUpRequestDto;
import com.petstagram.auth.repository.UserRepository;
import com.petstagram.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void singUp(String name, String email, String password) {
        User user = new User(name, email, password, 'Y');
         userRepository.save(user);
    }


}
