package com.petstagram.controller;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService service;
    @PostMapping
    public ResponseEntity<BoardResponseDto> create(/*@SessionAttribute Long id,*/@Valid @RequestBody CreateBoardRequestDto dto) {
        Long id = 1L;
        BoardResponseDto responseDto = service.create(dto,id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

}
