package com.petstagram.dto.board;

import lombok.Getter;

@Getter
public class UpdateBoardRequestDto {

    private String content;

    public UpdateBoardRequestDto() {
    }

    public UpdateBoardRequestDto(String content) {
        this.content = content;
    }
}