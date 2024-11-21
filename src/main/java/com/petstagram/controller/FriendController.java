package com.petstagram.controller;

import com.petstagram.dto.FriendAcceptDto;
import com.petstagram.dto.FriendRequestDto;
import com.petstagram.dto.FriendResponseDto;
import com.petstagram.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 친구 관련 기능을 처리하는 컨트롤러입니다.
 * 친구 요청, 친구 수락, 친구 삭제와 관련된 API 엔드포인트를 제공합니다.
 */
@RestController
@RequestMapping("/follow")  // "/follow" 경로로 시작하는 요청을 처리
@RequiredArgsConstructor  // 의존성 주입을 위한 생성자 자동 생성
public class FriendController {

    // FriendService를 주입받습니다.
    private final FriendService friendService;

    /**
     * 친구 요청을 처리하는 API.
     * 클라이언트로부터 친구 요청 정보를 받아서, 친구 요청을 처리하고 결과를 반환합니다.
     *
     * @param friendRequestDto 친구 요청 정보가 담긴 DTO
     * @return 친구 요청 결과를 담은 FriendResponseDto를 반환
     */
    @PostMapping("/request")
    public ResponseEntity<FriendResponseDto> requestFriend(@RequestBody FriendRequestDto friendRequestDto) {
        // 친구 요청을 처리하고 그 결과를 반환
        FriendResponseDto responseDto = friendService.requestFriend(friendRequestDto);
        return ResponseEntity.ok(responseDto);  // 성공 시 200 OK 상태와 함께 responseDto 반환
    }

    /**
     * 친구 요청을 수락하는 API.
     * 친구 수락 요청을 처리하고, 성공적으로 처리되었음을 응답합니다.
     *
     * @param friendAcceptDto 친구 수락 정보가 담긴 DTO
     * @return HTTP 상태 200 OK를 반환
     */
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptFriend(@RequestBody FriendAcceptDto friendAcceptDto) {
        // 친구 수락을 처리
        friendService.acceptFriend(friendAcceptDto);
        return ResponseEntity.ok().build();  // 성공적으로 수락되었음을 200 OK 상태로 응답
    }

    /**
     * 친구를 삭제하는 API.
     * 클라이언트로부터 친구 삭제 정보를 받아서, 해당 친구를 삭제합니다.
     *
     * @param friendRequestDto 삭제할 친구의 정보를 담은 DTO
     * @return HTTP 상태 200 OK를 반환
     */
    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteFriend(@RequestBody FriendRequestDto friendRequestDto) {
        // 친구 삭제를 처리
        friendService.deleteFriend(friendRequestDto);
        return ResponseEntity.ok().build();  // 성공적으로 삭제되었음을 200 OK 상태로 응답
    }
}
