package com.petstagram.repository;

import com.petstagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 주어진 ID에 해당하는 사용자 정보를 조회하고, 존재하지 않으면 예외를 발생시킴.
     *
     * @param id 사용자 ID
     * @return 해당 ID의 사용자 객체
     * @throws ResponseStatusException 존재하지 않는 사용자인 경우 예외 발생
     */
    default User findByIdOrElseThrows(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."));
    }

    /**
     * 주어진 이메일에 해당하는 사용자 정보를 조회.
     *
     * @param email 사용자 이메일
     * @return 해당 이메일의 사용자 객체 (없으면 Optional.empty())
     */
    Optional<User> findByEmail(String email);

    /**
     * 주어진 이메일에 해당하는 사용자 정보를 조회하고, 존재하지 않으면 예외를 발생시킴.
     *
     * @param email 사용자 이메일
     * @return 해당 이메일의 사용자 객체
     * @throws ResponseStatusException 존재하지 않는 사용자인 경우 예외 발생
     */
    default User findByUserOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, email + "에 해당하는 회원이 존재하지 않습니다."));
    }
}
