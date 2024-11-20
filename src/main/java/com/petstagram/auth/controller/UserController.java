package com.petstagram.auth.controller;


import com.petstagram.auth.dto.SignUpRequestDto;
import com.petstagram.auth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

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

        Member = loginMember = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        HttpSession session = request.getSession();
        session.setAttribute("USER_ID", loginMember.getId());

        return new ResponseEntity<>(HttpStatus.OK).body("")
    }
}
