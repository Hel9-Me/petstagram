package com.petstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendAcceptDto {
    private Long userFollowerId; // 친구 요청을 수락할 사용자 ID
}
