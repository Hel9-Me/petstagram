package com.petstagram.repository;

import com.petstagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrows(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다."));
    }

    Optional<User> findByEmail(String email);

    default User findByUserOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, email + "에 해당하는 회원이 존재하지 않습니다."));
    }
}
