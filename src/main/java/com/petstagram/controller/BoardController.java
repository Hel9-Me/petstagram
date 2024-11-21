package com.petstagram.controller;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import com.petstagram.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService service;
    @PostMapping
    public ResponseEntity<BoardResponseDto> create(/*@SessionAttribute Long id,*/@Valid @RequestBody CreateBoardRequestDto dto) {

        Long id = 1L; //session 추가시 삭제하기
        BoardResponseDto responseDto = service.create(dto,id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/{page}")
    public ResponseEntity<Page<BoardResponseDto>> find(/*@SessionAttribute Long id,*/@PathVariable int page) {
        Long id = 1L; //session 추가시 삭제하기
        Page<BoardResponseDto> boardResponseDtoPage =  service.find(id,page);
        return new ResponseEntity<>(boardResponseDtoPage, HttpStatus.OK);
    }
}
