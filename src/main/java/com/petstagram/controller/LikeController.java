package com.petstagram.controller;

import com.petstagram.dto.like.LikeRequestDto;
import com.petstagram.dto.like.LikeResponseDto;
import com.petstagram.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ResponseEntity<LikeResponseDto> likePost(@RequestBody LikeRequestDto dto) {
        LikeResponseDto responseDto = likeService.likePost(dto);
        return ResponseEntity.ok(responseDto);
    }


    @DeleteMapping
    public ResponseEntity<Void> unlikePost(@RequestBody LikeRequestDto dto) {
        likeService.unlikePost(dto);
        return ResponseEntity.noContent().build();
    }
}

