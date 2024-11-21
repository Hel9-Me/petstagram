package com.petstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FriendRequestDto는 친구 요청을 보내는 데 필요한 데이터를 담고 있는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 친구 요청을 보내는 사용자와 요청을 받는 사용자의 ID를 포함합니다.
 *
 * 주로 `FriendService`의 `requestFriend` 메소드에서 친구 요청을 처리하는 데 사용됩니다.
 *
 * Lombok을 사용하여 getter, 생성자 등을 자동으로 생성합니다.
 */
@Getter
@NoArgsConstructor // 기본 생성자를 자동 생성합니다.
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동 생성합니다.
public class FriendRequestDto {

    /**
     * 친구 요청을 보낸 사용자의 ID
     *
     * 이 ID는 친구 요청을 보낸 사용자를 식별하는데 사용됩니다.
     * 요청을 보낸 사용자에 대한 정보를 관리하는 데 필요합니다.
     */
    private Long userId; // 친구 요청을 보낸 사용자 ID

    /**
     * 친구 요청을 받은 사용자의 ID
     *
     * 이 ID는 친구 요청을 받은 사용자를 식별하는데 사용됩니다.
     * 요청을 받은 사용자에 대한 정보를 관리하는 데 필요합니다.
     */
    private Long userFollowerId; // 친구 요청을 받은 사용자 ID
}
