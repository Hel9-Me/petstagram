package com.petstagram.service;

import com.petstagram.dto.friend.FriendAcceptDto;
import com.petstagram.dto.friend.FriendRequestDto;
import com.petstagram.dto.friend.FriendResponseDto;

/**
 * FriendService 인터페이스는 친구 관계와 관련된 서비스 메서드를 정의한 인터페이스입니다.
 *
 * 이 인터페이스는 친구 요청, 친구 수락, 친구 삭제와 같은 친구 관리 기능을 제공합니다.
 * 이 인터페이스를 구현한 클래스에서 실제 비즈니스 로직을 구현하여 클라이언트의 요청에 대응합니다.
 */
public interface FriendService {

    /**
     * 친구 요청을 보낸 사용자와 받은 사용자의 친구 관계를 생성하는 메서드.
     *
     * @param friendRequestDto 친구 요청 DTO 객체
     * @return 요청이 처리된 후의 응답 DTO (수락 상태 포함)
     */
    FriendResponseDto requestFriend(FriendRequestDto friendRequestDto);

    /**
     * 친구 요청을 수락하는 메서드.
     *
     * @param friendAcceptDto 친구 수락 DTO 객체
     */
    void acceptFriend(FriendAcceptDto friendAcceptDto);

    /**
     * 친구 관계를 삭제하는 메서드.
     *
     * @param friendRequestDto 친구 관계를 삭제할 사용자 정보가 담긴 DTO 객체
     */
    void deleteFriend(FriendRequestDto friendRequestDto);
}
