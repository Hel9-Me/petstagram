package com.petstagram.controller;

import com.petstagram.dto.comment.CommentResponseDto;
import com.petstagram.dto.comment.CreateCommentRequestDto;
import com.petstagram.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public ResponseEntity<CommentResponseDto> create(@SessionAttribute(name = "USER_ID") Long userId,
                                                     @PathVariable Long boardId,
                                                     @Valid @RequestBody CreateCommentRequestDto dto) {
        CommentResponseDto commentResponseDto = commentService.create(userId, boardId, dto);
        return new ResponseEntity<>(commentResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentResponseDto>> find(@SessionAttribute(name = "USER_ID") Long userId,
                                                         @PathVariable Long boardId) {
        List<CommentResponseDto> commentResponseDtos = commentService.find(userId, boardId);
        return new ResponseEntity<>(commentResponseDtos, HttpStatus.OK);
    }

}
