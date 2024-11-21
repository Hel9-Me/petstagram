package com.petstagram.service.imp;

import com.petstagram.dto.user.ProfileResponseDto;
import com.petstagram.enums.AccountStatus;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.UserService;
import com.petstagram.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public ProfileResponseDto getProfile(Long id) {
        User findUser = userRepository.findByIdOrElseThrows(id);

        if (AccountStatus.NOT_USE.equals(findUser.getUseyn())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 탈퇴한 회원입니다.");
        }

        return new ProfileResponseDto(findUser);
    }

    public ProfileResponseDto updateProfile(Long id, String newName, String password, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrows(id);

        if(!PasswordEncoder.matches(password, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        if(password.equals(newPassword)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "동일한 비밀번호로 변경할 수 없습니다.");
        }

        String hashedPassword = PasswordEncoder.encode(newPassword);

        findUser.update(newName, hashedPassword);
        User savedUser = userRepository.save(findUser);

        return new ProfileResponseDto(savedUser);
    }
}
