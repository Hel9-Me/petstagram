package com.petstagram.dto.user;

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
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.createdAt = user.getCreatedAt();
    }
}
