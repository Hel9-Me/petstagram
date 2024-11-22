package com.petstagram.service.imp;

import com.petstagram.common.constants.AccountStatus;
import com.petstagram.common.constants.UserErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.dto.user.ProfileResponseDto;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.UserService;
import com.petstagram.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public ProfileResponseDto getProfile(Long id) {
        User findUser = userRepository.findByIdOrElseThrows(id);

        if (AccountStatus.NOT_USE.equals(findUser.getUseyn())) {
            throw new CustomException(UserErrorCode.DEACTIVATED_USER);
        }

        return new ProfileResponseDto(findUser);
    }

    public ProfileResponseDto updateProfile(Long id, String newName, String password, String newPassword) {
        User findUser = userRepository.findByIdOrElseThrows(id);

        if(!PasswordEncoder.matches(password, findUser.getPassword())){
            throw new CustomException(UserErrorCode.PASSWORD_INCORRECT);
        }

        if(password.equals(newPassword)) {
            throw new CustomException(UserErrorCode.INVALID_PASSWORD);
        }

        String hashedPassword = PasswordEncoder.encode(newPassword);

        findUser.update(newName, hashedPassword);
        User savedUser = userRepository.save(findUser);

        return new ProfileResponseDto(savedUser);
    }
}
