package com.petstagram.service;

import com.petstagram.dto.FriendAcceptDto;
import com.petstagram.dto.FriendRequestDto;
import com.petstagram.dto.FriendResponseDto;
import com.petstagram.model.entity.Friend;
import com.petstagram.model.entity.User;
import com.petstagram.repository.FriendRepository;
import com.petstagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendResponseDto requestFriend(FriendRequestDto friendRequestDto) {

        User user = userRepository.findByIdOrElseThrows(friendRequestDto.getUserId());
        User userFollower = userRepository.findByIdOrElseThrows(friendRequestDto.getUserFollowerId());

        Friend friend = new Friend(user, userFollower);
        friendRepository.save(friend);

        return new FriendResponseDto(userFollower.getId(), false);
    }

    public void acceptFriend(FriendAcceptDto friendAcceptDto) {
        User userFollower = userRepository.findByIdOrElseThrows(friendAcceptDto.getUserFollowerId());
        Friend friend = friendRepository.findByUserFollowerAndIsAcceptedFalse(userFollower)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "친구 요청을 찾을 수 없습니다."));

        friend.accept();
        friendRepository.save(friend);
    }

    public void deleteFriend(FriendRequestDto friendRequestDto) {
        User user = userRepository.findByIdOrElseThrows(friendRequestDto.getUserId());
        User userFollower = userRepository.findByIdOrElseThrows(friendRequestDto.getUserFollowerId());

        Friend friend = friendRepository.findByUserAndUserFollower(user, userFollower)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "친구 관계를 찾을 수 없습니다."));

        friendRepository.delete(friend);
    }
}
