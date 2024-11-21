package com.petstagram.dto.board;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateBoardRequestDto {
    @NotNull
    String content;
}
