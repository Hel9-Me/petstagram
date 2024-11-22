package com.petstagram.repository;

import com.petstagram.common.constants.UserErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    default User findByIdOrElseThrows(Long id) {
        return findById(id).orElseThrow(() -> new CustomException(UserErrorCode.NOT_FOUND));
    }

    Optional<User> findByEmail(String email);

    default User findByUserOrElseThrow(String email) {
        return findByEmail(email).orElseThrow(()-> new CustomException(UserErrorCode.NOT_FOUND));
    }
}