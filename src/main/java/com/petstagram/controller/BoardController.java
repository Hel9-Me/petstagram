package com.petstagram.controller;

import com.petstagram.dto.board.BoardResponseDto;
import com.petstagram.dto.board.CreateBoardRequestDto;
import com.petstagram.dto.board.UpdateBoardRequestDto;
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
    public ResponseEntity<BoardResponseDto> create(@SessionAttribute("USER_ID") Long id,@Valid @RequestBody CreateBoardRequestDto dto) {

        BoardResponseDto responseDto = service.create(dto,id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/{page}")
    public ResponseEntity<Page<BoardResponseDto>> find(@SessionAttribute("USER_ID") Long id,@PathVariable int page) {
        Page<BoardResponseDto> boardResponseDtoPage =  service.find(id,page+1);
        return new ResponseEntity<>(boardResponseDtoPage, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@SessionAttribute(name = "USER_ID", required = false) Long userId, @RequestBody UpdateBoardRequestDto requestDto, @PathVariable Long id) {

        service.updateById(userId, requestDto.getContent(), id);

        return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@SessionAttribute(name = "USER_ID", required = false) Long userId, @PathVariable Long id) {

        service.deleteById(userId, id);

        return new ResponseEntity<>("게시글이 삭제되었습니다.",HttpStatus.OK);
    }

}
