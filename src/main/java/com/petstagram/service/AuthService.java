package com.petstagram.service;

import com.petstagram.repository.UserRepository;
import com.petstagram.util.PasswordEncoder;
import com.petstagram.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public  void singUp(String name, String email, String password) {

        Optional<User> findEmail = userRepository.findByEmail(email);

        if (findEmail.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 이메일로는 가입 할 수 없습니다.");
        }

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
