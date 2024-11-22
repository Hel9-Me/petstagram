package com.petstagram.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequestDto {
    @NotNull
    String comment;
}
