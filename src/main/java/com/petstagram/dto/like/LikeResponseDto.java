package com.petstagram.dto.like;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LikeResponseDto {
    private Long likeId;
    private Long userId;
    private Long boardId;
    private Long commentId;
}
