package com.petstagram.controller;

import com.petstagram.dto.ProfileRequestDto;
import com.petstagram.dto.ProfileResponseDto;
import com.petstagram.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userservice;

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> getProfile(@PathVariable Long id) {
        ProfileResponseDto responseDto = userservice.getProfile(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ProfileResponseDto> updateProfile(
            @PathVariable Long id,
            @Valid @RequestBody ProfileRequestDto requestDto
    ){

        ProfileResponseDto responseDto = userservice.updateProfile(id, requestDto.getName(), requestDto.getPassword(), requestDto.getNewPassword());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
