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
    public ResponseEntity<String> singUp(@Valid @RequestBody SignUpRequestDto requestDto){

       userService.singUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        return new ResponseEntity<>("회원 가입을 성공 했습니다.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){

        User loginUser = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        HttpSession session = request.getSession();
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

