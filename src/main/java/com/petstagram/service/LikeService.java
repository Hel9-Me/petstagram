package com.petstagram.service;

import com.petstagram.dto.like.LikeRequestDto;
import com.petstagram.dto.like.LikeResponseDto;

public interface LikeService {
    LikeResponseDto likePost(LikeRequestDto dto);
    void unlikePost(LikeRequestDto dto);
}
