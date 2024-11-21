package com.petstagram.controller;

import com.petstagram.dto.auth.LoginRequestDto;
import com.petstagram.dto.auth.SignUpRequestDto;
import com.petstagram.service.AuthService;
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

/**
 * AuthController 클래스는 사용자 인증 관련 API 엔드포인트를 제공하는 컨트롤러입니다.
 *
 * 회원가입, 로그인, 로그아웃 및 계정 탈퇴와 같은 인증 관련 기능을 처리합니다.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService userService;

    /**
     * 회원가입 API 엔드포인트입니다.
     *
     * 사용자가 이름, 이메일, 비밀번호를 포함하는 회원가입 요청을 보냄으로써 새로운 계정을 생성합니다.
     *
     * @param requestDto 회원가입에 필요한 데이터(이름, 이메일, 비밀번호)를 포함한 DTO 객체
     * @return 회원가입 성공 메시지와 HTTP 201 상태 코드
     */
    @PostMapping("/signup")
    public ResponseEntity<String> singUp(@Valid @RequestBody SignUpRequestDto requestDto){

        // 사용자 서비스에서 회원가입 로직을 실행
        userService.singUp(
                requestDto.getName(),
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        // 회원가입 성공 메시지와 함께 201 CREATED 상태 코드 반환
        return new ResponseEntity<>("회원 가입을 성공 했습니다.", HttpStatus.CREATED);
    }

    /**
     * 로그인 API 엔드포인트입니다.
     *
     * 사용자가 이메일과 비밀번호를 제공하여 로그인합니다.
     * 로그인 성공 후 세션에 사용자 ID를 저장하여 인증된 상태를 유지합니다.
     * 이미 로그인된 상태에서는 로그인 할 수 없도록 처리합니다.
     *
     * @param requestDto 로그인에 필요한 이메일과 비밀번호를 포함한 DTO 객체
     * @param request HttpServletRequest 객체를 통해 세션을 처리
     * @return 로그인 성공 메시지와 HTTP 200 상태 코드
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDto requestDto, HttpServletRequest request){

        // 사용자 서비스에서 로그인 처리
        User loginUser = userService.login(
                requestDto.getEmail(),
                requestDto.getPassword()
        );

        // 세션 객체 가져오기
        HttpSession session = request.getSession();

        // 이미 로그인된 상태에서는 로그인 불가
        if (session.getAttribute("USER_ID") != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미 로그인 되었습니다.");
        }

        // 로그인된 사용자 ID를 세션에 저장
        session.setAttribute("USER_ID", loginUser.getId());

        // 로그인 성공 메시지와 함께 200 OK 상태 코드 반환
        return new ResponseEntity<>("로그인이 성공 했습니다. ",HttpStatus.OK);
    }

    /**
     * 로그아웃 API 엔드포인트입니다.
     *
     * 사용자가 로그아웃을 요청하면 세션을 무효화하여 인증 정보를 삭제합니다.
     *
     * @param request HttpServletRequest 객체를 통해 세션을 처리
     * @return 로그아웃 성공 메시지와 HTTP 200 상태 코드
     */
    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request){

        // 현재 세션 가져오기 (없으면 null 반환)
        HttpSession session = request.getSession(false);

        // 세션이 존재하면 무효화 처리
        if (session != null){
            session.invalidate();
        }

        // 로그아웃 성공 메시지와 함께 200 OK 상태 코드 반환
        return new ResponseEntity<>("로그아웃이 되었습니다.", HttpStatus.OK);
    }

    /**
     * 계정 탈퇴 API 엔드포인트입니다.
     *
     * 사용자가 탈퇴 요청을 보내면 해당 사용자의 계정을 삭제하고,
     * 이후 로그아웃 처리를 수행하여 세션을 무효화합니다.
     *
     * @param userId 세션에 저장된 사용자 ID
     * @param request HttpServletRequest 객체를 통해 세션을 처리
     * @return 탈퇴 완료 메시지와 HTTP 200 상태 코드
     */
    @DeleteMapping("/leave")
    public ResponseEntity<String> leave(@SessionAttribute("USER_ID") Long userId, HttpServletRequest request){

        // 사용자 서비스에서 탈퇴 처리
        userService.leave(userId);
        log.info("{}", userId);

        // 탈퇴 완료 후 세션 무효화 (로그아웃)
        HttpSession session = request.getSession(false);
        if (session != null){
            session.invalidate();
        }

        // 탈퇴 완료 메시지와 함께 200 OK 상태 코드 반환
        return ResponseEntity.ok().body("탈퇴가 완료되었습니다. 이용해 주셔서 감사합니다.");
    }
}
