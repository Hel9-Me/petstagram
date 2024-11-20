package com.petstagram.controller;


import com.petstagram.service.AuthService;
import com.petstagram.dto.ProfileResponseDto;
import com.petstagram.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {


    private final AuthService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@Valid @RequestBody ProfileResponseDto.SignUpRequestDto requestDto){

       userService.singUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return new ResponseEntity<>("회원 가입을 성공 했습니다.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody ProfileResponseDto.LoginRequestDto requestDto, HttpServletRequest request){

        User loginUser = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        HttpSession session = request.getSession();

        if (session.getAttribute("USER_ID") != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 로그인 되었습니다.");
        }

        session.setAttribute("USER_ID", loginUser.getId());

        return new ResponseEntity<>("로그인이 성공 했습니다. ",HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }

        return new ResponseEntity<>("로그아웃이 되었습니다.", HttpStatus.OK);
    }
}

