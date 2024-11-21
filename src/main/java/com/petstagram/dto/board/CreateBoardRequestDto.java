package com.petstagram.dto.board;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 게시글 생성 요청을 위한 DTO 클래스.
 * 게시글을 생성할 때 필요한 정보를 포함합니다.
 */
@Getter
public class CreateBoardRequestDto {

    /**
     * 게시글 내용. 빈 값일 수 없도록 검증.
     *
     * @NotNull 게시글 내용은 반드시 존재해야 함
     */
    @NotNull
    private String content;
}
