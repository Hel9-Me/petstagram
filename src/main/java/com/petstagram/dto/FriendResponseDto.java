package com.petstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendResponseDto {
    private Long userFollowerId;  // 요청을 보낸 사용자 ID
    private boolean isAccepted; // 친구 요청 수락 상태
}
