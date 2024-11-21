package com.petstagram.repository;

import com.petstagram.model.entity.Friend;
import com.petstagram.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 친구 관계에 관련된 데이터베이스 작업을 처리하는 레포지토리 인터페이스입니다.
 * 이 인터페이스는 JpaRepository를 상속받아, CRUD 연산 및 데이터 조회 작업을 수행합니다.
 */
@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {

    /**
     * 특정 사용자가 특정 사용자를 팔로우하는 관계를 찾는 메서드입니다.
     * 친구 관계에서 `User`와 `UserFollower`가 일치하는 `Friend` 엔티티를 조회합니다.
     *
     * @param user 친구 요청을 보낸 사용자
     * @param userFollower 친구 요청을 받은 사용자
     * @return 사용자 간의 친구 관계를 담은 Optional<Friend> 객체
     */
    Optional<Friend> findByUserAndUserFollower(User user, User userFollower);

    /**
     * 특정 사용자가 수락하지 않은 친구 요청을 조회하는 메서드입니다.
     * `UserFollower`가 팔로우한 사용자의 친구 요청 중에서 수락되지 않은 요청을 찾습니다.
     *
     * @param userFollower 친구 요청을 받은 사용자 (팔로워)
     * @return 수락되지 않은 친구 요청을 담은 Optional<Friend> 객체
     */
    Optional<Friend> findByUserFollowerAndIsAcceptedFalse(User userFollower);
}
