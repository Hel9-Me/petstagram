package com.petstagram.auth.controller;


import com.petstagram.auth.dto.LoginRequestDto;
import com.petstagram.auth.dto.SignUpRequestDto;
import com.petstagram.auth.service.AuthService;
import com.petstagram.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService userService;

    @PostMapping("/signup")
    public ResponseEntity<Void> singUp(@Valid @RequestBody SignUpRequestDto requestDto){

        userService.singUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){

        User loginUser = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        HttpSession session = request.getSession();
        session.setAttribute("USER_ID", loginUser.getId());

        return new ResponseEntity<>("로그인이 성공했습니다. ",HttpStatus.OK);
    }
}
