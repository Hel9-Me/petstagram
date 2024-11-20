package com.petstagram.dto;

import com.petstagram.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ProfileResponseDto {

    private Long id;
    private String name;
    private String email;
    private Timestamp createdAt;

    public ProfileResponseDto(User user) {
        this.id = user.getId();;
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }

    @Getter
    public static class LoginRequestDto {

        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "올바른 이메일 형식을 입력해 주세요")
        private final String email;

        @NotBlank // 패턴 논의 전
        private final String password;

        public LoginRequestDto(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    @Getter
    public static class SignUpRequestDto {

        @NotBlank(message = "올바른 이름을 입력해주세요")
        private final String name;

        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "올바른 이메일 형식을 입력해 주세요")
        private final String email;

        // 패턴 논의되지 않았음, 우선 보류
        @NotBlank
        private final String password;

        public SignUpRequestDto(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }

    }
}
