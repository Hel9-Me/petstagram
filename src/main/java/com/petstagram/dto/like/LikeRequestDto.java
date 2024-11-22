package com.petstagram.dto.like;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LikeRequestDto {
    private Long userId; // 유저 ID
    private Long boardId; // 게시글 ID (nullable)
    private Long commentId; // 댓글 ID (nullable)
}
