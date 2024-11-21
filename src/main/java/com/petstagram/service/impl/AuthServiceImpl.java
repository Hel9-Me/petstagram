package com.petstagram.service.impl;

import com.petstagram.model.entity.enums.AccountStatus;
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

/**
 * 인증 관련 서비스 구현 클래스.
 * 회원 가입, 로그인, 탈퇴 등의 인증 기능을 처리합니다.
 */
@Service
@RequiredArgsConstructor  // 생성자 주입을 위해 Lombok 어노테이션 사용
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;  // UserRepository를 통해 사용자 데이터 처리

    /**
     * 회원 가입 처리 메서드.
     * 사용자가 입력한 이메일로 중복 여부를 확인하고, 비밀번호를 암호화하여 새 사용자 정보를 저장합니다.
     *
     * @param name 사용자의 이름
     * @param email 사용자의 이메일 (고유해야 함)
     * @param password 사용자의 비밀번호
     * @throws ResponseStatusException 이메일 중복 시 Conflict 상태 코드 반환
     *                                  탈퇴한 회원 재가입 시 Forbidden 상태 코드 반환
     */
    public void singUp(String name, String email, String password) {

        // 이메일로 사용자가 이미 존재하는지 확인
        Optional<User> findUser = userRepository.findByEmail(email);

        if (findUser.isPresent()) {

            // 탈퇴한 회원 재가입 시 예외 처리
            if (AccountStatus.N.equals(findUser.get().getUseyn())) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 탈퇴한 회원으로 계정이 비활성화 되었습니다.");
            }

            // 이메일 중복 시 예외 처리
            throw new ResponseStatusException(HttpStatus.CONFLICT, "중복된 이메일로는 가입 할 수 없습니다.");
        }

        // 비밀번호를 암호화하여 저장
        String hashedPassword = PasswordEncoder.encode(password);

        // 새로운 사용자 객체 생성 및 저장
        User user = new User(name, email, hashedPassword, AccountStatus.Y);
        userRepository.save(user);
    }

    /**
     * 로그인 처리 메서드.
     * 사용자의 이메일로 로그인하여 계정 활성화 상태를 확인하고, 비밀번호를 비교하여 로그인 여부를 결정합니다.
     *
     * @param email 사용자의 이메일
     * @param password 사용자의 비밀번호
     * @return 로그인 성공 시 User 객체 반환
     * @throws ResponseStatusException 비밀번호 불일치 또는 탈퇴한 회원일 경우 예외 발생
     */
    public User login(String email, String password) {
        // 이메일로 사용자 조회
        User findUser = userRepository.findByUserOrElseThrow(email);

        // 탈퇴한 회원 로그인 처리 예외
        if (AccountStatus.N.equals(findUser.getUseyn())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이미 탈퇴한 회원으로 로그인이 불가합니다.");
        }

        // 비밀번호가 일치하지 않으면 Unauthorized 예외 발생
        if (!PasswordEncoder.matches(password, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return findUser;  // 로그인 성공 시 사용자 객체 반환
    }

    /**
     * 탈퇴 처리 메서드.
     * 사용자가 탈퇴하면 해당 계정의 상태를 비활성화로 변경합니다.
     *
     * @param userId 탈퇴할 사용자 ID
     */
    @Transactional  // 데이터베이스 트랜잭션을 보장
    public void leave(Long userId) {
        // 사용자를 ID로 조회하고, 없으면 예외 처리
        User findUser = userRepository.findByIdOrElseThrows(userId);

        // 계정 상태를 비활성화로 변경
        findUser.disableUserAccount(AccountStatus.N);
    }
}
