package com.petstagram.controller;

import com.petstagram.dto.FriendAcceptDto;
import com.petstagram.dto.FriendRequestDto;
import com.petstagram.dto.FriendResponseDto;
import com.petstagram.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/follow")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/request")
    public ResponseEntity<FriendResponseDto> requestFriend(@RequestBody FriendRequestDto friendRequestDto) {
        FriendResponseDto responseDto = friendService.requestFriend(friendRequestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/accept")
    public ResponseEntity<Void> acceptFriend(@RequestBody FriendAcceptDto friendAcceptDto) {
        friendService.acceptFriend(friendAcceptDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/remove")
    public ResponseEntity<Void> deleteFriend(@RequestBody FriendRequestDto friendRequestDto) {
        friendService.deleteFriend(friendRequestDto);
        return ResponseEntity.ok().build();
    }
}
