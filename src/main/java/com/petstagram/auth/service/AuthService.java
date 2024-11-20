package com.petstagram.auth.service;

import com.petstagram.auth.repository.UserRepository;
import com.petstagram.auth.util.PasswordEncoder;
import com.petstagram.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public void singUp(String name, String email, String password) {

        String hashedPassword = PasswordEncoder.encode(password);

        User user = new User(name, email, hashedPassword, 'Y');

        userRepository.save(user);
    }


    public User login(String email, String password) {
        User findUser = userRepository.findByUserOrElseThrow(email);

        if(!PasswordEncoder.matches(password, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return findUser;
    }
}
