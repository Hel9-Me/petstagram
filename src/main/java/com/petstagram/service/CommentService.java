package com.petstagram.service;

import com.petstagram.dto.comment.CommentRequestDto;
import com.petstagram.dto.comment.CommentResponseDto;
import com.petstagram.dto.comment.CreateCommentRequestDto;

import java.util.List;

public interface CommentService {
    CommentResponseDto create(Long userId, Long boardId, CreateCommentRequestDto dto);

    List<CommentResponseDto> find(Long userId, Long boardId);

    CommentResponseDto update(Long userId, Long commentId, CommentRequestDto dto);
}
