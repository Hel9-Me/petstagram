package com.petstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequestDto {
    private Long userId; // 친구 요청을 보낸 사용자 ID
    private Long userFollowerId; // 친구 요청을 받은 사용자 ID

}
