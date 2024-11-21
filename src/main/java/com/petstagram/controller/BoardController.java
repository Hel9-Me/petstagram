package com.petstagram.controller;

import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import com.petstagram.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * BoardController는 게시판 관련 API 엔드포인트를 제공합니다.
 * 게시글을 생성하거나 조회하는 기능을 처리합니다.
 */
@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    // BoardService를 주입받아 게시판 관련 비즈니스 로직을 처리
    private final BoardService service;

    /**
     * 게시글을 생성하는 API 엔드포인트입니다.
     *
     * @param id 세션에서 가져온 사용자 ID
     * @param dto 게시글 생성에 필요한 데이터 (제목, 내용 등)
     * @return 생성된 게시글에 대한 응답 DTO와 HTTP 200 상태 코드
     */
    @PostMapping
    public ResponseEntity<BoardResponseDto> create(@SessionAttribute("USER_ID") Long id, @Valid @RequestBody CreateBoardRequestDto dto) {

        // 서비스에서 게시글 생성 처리 후 응답 DTO 반환
        BoardResponseDto responseDto = service.create(dto, id);

        // 생성된 게시글 정보를 반환하며, HTTP 200 상태 코드 반환
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 페이지별로 게시글을 조회하는 API 엔드포인트입니다.
     *
     * @param id 세션에서 가져온 사용자 ID
     * @param page 페이지 번호 (1-based)
     * @return 게시글 목록을 포함한 페이지 객체와 HTTP 200 상태 코드
     */
    @GetMapping("/{page}")
    public ResponseEntity<Page<BoardResponseDto>> find(@SessionAttribute("USER_ID") Long id, @PathVariable int page) {

        // 서비스에서 해당 페이지에 맞는 게시글 조회
        Page<BoardResponseDto> boardResponseDtoPage = service.find(id, page + 1);

        // 조회된 게시글 목록을 반환하며, HTTP 200 상태 코드 반환
        return new ResponseEntity<>(boardResponseDtoPage, HttpStatus.OK);
    }
}
