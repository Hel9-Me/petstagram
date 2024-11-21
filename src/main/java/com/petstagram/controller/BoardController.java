package com.petstagram.controller;

import com.petstagram.model.dto.BoardResponseDto;
import com.petstagram.model.dto.CreateBoardRequestDto;
import com.petstagram.service.BoardService;
import com.petstagram.util.ImgUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService service;
    private final ImgUtils imgUtils;

    @PostMapping
    public ResponseEntity<BoardResponseDto> create(@SessionAttribute("USER_ID") Long id,
                                                   @RequestPart(name = "img",required = false) List<MultipartFile> multipartFiles,
                                                   @Valid @RequestPart(name = "board") CreateBoardRequestDto dto) {


        BoardResponseDto responseDto = service.create(dto,id,multipartFiles);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/{page}")
    public ResponseEntity<Page<BoardResponseDto>> find(@SessionAttribute("USER_ID") Long id,@PathVariable int page) {
        Page<BoardResponseDto> boardResponseDtoPage =  service.find(id,page+1);
        return new ResponseEntity<>(boardResponseDtoPage, HttpStatus.OK);
    }

}
