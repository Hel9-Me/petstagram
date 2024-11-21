package com.petstagram.service.imp;

import com.petstagram.enums.AccountStatus;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.AuthService;
import com.petstagram.util.PasswordEncoder;
import com.petstagram.model.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public  void singUp(String name, String email, String password) {

        Optional<User> findUser = userRepository.findByEmail(email);

        if (findUser.isPresent()) {

            // 탈퇴한 회원 회원가입 시 예외처리
            // TODO: 탈퇴 사용자 재가입 시 어떤 상태 코드로 예외처리를 해야할 지 의논 필요
            if (AccountStatus.NOT_USE.equals(findUser.get().getUseyn())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 탈퇴한 회원으로 계정이 비활성화 되었습니다.");
            }

            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 이메일로는 가입 할 수 없습니다.");
        }


        String hashedPassword = PasswordEncoder.encode(password);

        User user = new User(name, email, hashedPassword, AccountStatus.USE);

        userRepository.save(user);
    }


    public User login(String email, String password) {
        User findUser = userRepository.findByUserOrElseThrow(email);

        // 탈퇴한 회원 로그인 예외처리
        if (AccountStatus.NOT_USE.equals(findUser.getUseyn())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 탈퇴한 회원으로 로그인이 불가합니다.");
        }

        if(!PasswordEncoder.matches(password, findUser.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return findUser;
    }

    @Transactional
    public void leave(Long userId) {
        User findUser = userRepository.findByIdOrElseThrows(userId);

        findUser.disableUserAccount(AccountStatus.NOT_USE);
    }
}
