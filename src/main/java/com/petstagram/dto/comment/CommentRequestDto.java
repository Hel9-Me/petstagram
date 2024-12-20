package com.petstagram.dto.comment;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    @Size(min = 8, message = "비밀번호는 8글자 이상으로 입력해주세요.")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,15}$", message = "비밀번호는 대소문자 포함 영문 + 숫자 + 특수문자 최소 1글자씩 입력해주세요.")
    private String password;


    @NotNull
    private String comment;
}
