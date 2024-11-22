package com.petstagram.service.imp;

import com.petstagram.common.constants.AccountStatus;
import com.petstagram.common.constants.UserErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.model.entity.User;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.AuthService;
import com.petstagram.util.PasswordEncoder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public  void singUp(String name, String email, String password) {

        Optional<User> findUser = userRepository.findByEmail(email);

        if (findUser.isPresent()) {

            // 탈퇴한 회원 회원가입 시 예외처리
            if (AccountStatus.NOT_USE.equals(findUser.get().getUseyn())) {
                throw new CustomException(UserErrorCode.NOT_FOUND);
            }

            throw new CustomException(UserErrorCode.DUPLICATED_EMAIL);
        }


        String hashedPassword = PasswordEncoder.encode(password);

        User user = new User(name, email, hashedPassword, AccountStatus.USE);

        userRepository.save(user);
    }


    public User login(String email, String password) {
        User findUser = userRepository.findByUserOrElseThrow(email);

        // 탈퇴한 회원 로그인 예외처리
        if (AccountStatus.NOT_USE.equals(findUser.getUseyn())) {
            throw new CustomException(UserErrorCode.DEACTIVATED_USER);
        }

        if(!PasswordEncoder.matches(password, findUser.getPassword())){
            throw new CustomException(UserErrorCode.PASSWORD_INCORRECT);
        }

        return findUser;
    }

    @Transactional
    public void leave(Long userId) {
        User findUser = userRepository.findByIdOrElseThrows(userId);

        findUser.disableUserAccount(AccountStatus.NOT_USE);
    }
}
