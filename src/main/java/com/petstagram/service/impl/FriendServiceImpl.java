package com.petstagram.service.impl;

import com.petstagram.dto.friend.FriendAcceptDto;
import com.petstagram.dto.friend.FriendRequestDto;
import com.petstagram.dto.friend.FriendResponseDto;
import com.petstagram.model.entity.Friend;
import com.petstagram.model.entity.User;
import com.petstagram.repository.FriendRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * 친구 관계를 관리하는 서비스 클래스입니다.
 * 이 클래스는 친구 요청, 수락, 삭제 등 친구와 관련된 주요 비즈니스 로직을 처리합니다.
 */
@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;  // 친구 관계를 관리하는 레포지토리
    private final UserRepository userRepository;      // 사용자 정보를 관리하는 레포지토리

    /**
     * 친구 요청을 보내는 메서드입니다.
     * 친구 요청을 보낸 사용자와 받은 사용자의 정보를 받아 `Friend` 객체를 생성하여 데이터베이스에 저장합니다.
     *
     * @param friendRequestDto 친구 요청에 필요한 데이터가 담긴 DTO 객체
     * @return 친구 요청을 보낸 사용자의 정보를 담은 응답 DTO
     */
    public FriendResponseDto requestFriend(FriendRequestDto friendRequestDto) {

        // 요청한 사용자의 정보 조회 (사용자가 존재하지 않으면 예외 발생)
        User user = userRepository.findByIdOrElseThrows(friendRequestDto.getUserId());

        // 팔로워(친구 요청을 받은) 사용자의 정보 조회 (사용자가 존재하지 않으면 예외 발생)
        User userFollower = userRepository.findByIdOrElseThrows(friendRequestDto.getUserFollowerId());

        // 친구 요청을 보내는 사용자와 받은 사용자 간의 Friend 엔티티 생성
        Friend friend = new Friend(user, userFollower);

        // 데이터베이스에 Friend 엔티티 저장
        friendRepository.save(friend);

        // 친구 요청을 보낸 사용자 정보와 요청 수락 여부(false) 반환
        return new FriendResponseDto(userFollower.getId(), false);
    }

    /**
     * 친구 요청을 수락하는 메서드입니다.
     * 친구 요청을 수락하면 해당 친구 요청의 상태를 '수락됨'으로 변경합니다.
     *
     * @param friendAcceptDto 친구 수락에 필요한 데이터가 담긴 DTO 객체
     */
    public void acceptFriend(FriendAcceptDto friendAcceptDto) {

        // 팔로워(친구 요청을 받은) 사용자의 정보 조회 (사용자가 존재하지 않으면 예외 발생)
        User userFollower = userRepository.findByIdOrElseThrows(friendAcceptDto.getUserFollowerId());

        // 수락되지 않은 친구 요청을 찾음 (친구 관계가 존재하지 않으면 예외 발생)
        Friend friend = friendRepository.findByUserFollowerAndIsAcceptedFalse(userFollower)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "친구 요청을 찾을 수 없습니다."));

        // 친구 요청을 수락 처리
        friend.accept();

        // 변경된 친구 관계 저장
        friendRepository.save(friend);
    }

    /**
     * 친구 관계를 삭제하는 메서드입니다.
     * 친구 관계를 삭제하려면 두 사용자의 친구 관계를 찾아 삭제합니다.
     *
     * @param friendRequestDto 친구 삭제에 필요한 데이터가 담긴 DTO 객체
     */
    public void deleteFriend(FriendRequestDto friendRequestDto) {

        // 요청한 사용자의 정보 조회 (사용자가 존재하지 않으면 예외 발생)
        User user = userRepository.findByIdOrElseThrows(friendRequestDto.getUserId());

        // 팔로워(친구 요청을 받은) 사용자의 정보 조회 (사용자가 존재하지 않으면 예외 발생)
        User userFollower = userRepository.findByIdOrElseThrows(friendRequestDto.getUserFollowerId());

        // 두 사용자의 친구 관계를 찾아서 삭제 (친구 관계가 존재하지 않으면 예외 발생)
        Friend friend = friendRepository.findByUserAndUserFollower(user, userFollower)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "친구 관계를 찾을 수 없습니다."));

        // 친구 관계 삭제
        friendRepository.delete(friend);
    }
}
