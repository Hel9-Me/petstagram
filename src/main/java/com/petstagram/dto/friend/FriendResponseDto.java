package com.petstagram.dto.friend;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * FriendResponseDto는 친구 요청에 대한 응답을 처리하는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 친구 요청을 보낸 사용자 ID와 해당 요청의 수락 상태를 포함합니다.
 *
 * 주로 `FriendService`에서 친구 요청을 처리한 후, 응답을 반환할 때 사용됩니다.
 *
 * Lombok을 사용하여 getter, 생성자 등을 자동으로 생성합니다.
 */
@Getter
public class FriendResponseDto {

    /**
     * 요청을 보낸 사용자의 ID
     *
     * 이 ID는 친구 요청을 보낸 사용자를 식별하는데 사용됩니다.
     * 요청을 보낸 사용자에 대한 정보를 관리하는 데 필요합니다.
     */
    private Long userFollowerId;  // 요청을 보낸 사용자 ID

    /**
     * 친구 요청의 수락 상태
     *
     * 이 값은 친구 요청이 수락되었는지 거절되었는지를 나타냅니다.
     * true이면 수락된 상태, false이면 수락되지 않은 상태입니다.
     */
    private boolean isAccepted; // 친구 요청 수락 상태

    public FriendResponseDto(Long userFollowerId, boolean isAccepted) {
        this.userFollowerId = userFollowerId;
        this.isAccepted = isAccepted;
    }
}
