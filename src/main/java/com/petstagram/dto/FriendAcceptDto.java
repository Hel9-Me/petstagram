package com.petstagram.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FriendAcceptDto는 친구 요청 수락에 필요한 데이터를 담고 있는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 친구 요청을 수락하려는 사용자 정보를 전달하는 데 사용됩니다.
 *
 * 주로 `FriendService`의 `acceptFriend` 메소드에서 친구 요청 수락을 처리하는 데 사용됩니다.
 * 이 클래스에는 친구 요청을 수락하는 사용자의 ID만 포함됩니다.
 *
 * Lombok을 사용하여 getter, 생성자 등을 자동으로 생성합니다.
 */
@Getter
@NoArgsConstructor // 기본 생성자를 자동 생성합니다.
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자를 자동 생성합니다.
public class FriendAcceptDto {

    /**
     * 친구 요청을 수락할 사용자의 ID
     *
     * 이 ID는 친구 요청을 수락하려는 사용자를 식별하는데 사용됩니다.
     * 사용자가 친구 요청을 수락하기 위해서는 해당 사용자의 ID가 필요합니다.
     */
    private Long userFollowerId; // 친구 요청을 수락할 사용자 ID
}
