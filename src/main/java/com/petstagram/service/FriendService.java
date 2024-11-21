package com.petstagram.service;

import com.petstagram.dto.friend.FriendAcceptDto;
import com.petstagram.dto.friend.FriendRequestDto;
import com.petstagram.dto.friend.FriendResponseDto;

public interface FriendService {
    FriendResponseDto requestFriend(FriendRequestDto friendRequestDto);
    void acceptFriend(FriendAcceptDto friendAcceptDto);
    void deleteFriend(FriendRequestDto friendRequestDto);
}
