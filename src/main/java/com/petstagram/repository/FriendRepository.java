package com.petstagram.repository;

import com.petstagram.model.entity.Friend;
import com.petstagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    Optional<Friend> findByUserAndUserFollower(User user, User userFollower);
    Optional<Friend> findByUserFollowerAndIsAcceptedFalse(User userFollower);
}
