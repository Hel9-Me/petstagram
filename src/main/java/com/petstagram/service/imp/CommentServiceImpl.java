package com.petstagram.service.imp;

import com.petstagram.common.constants.BoardErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.dto.CommentResponseDto;
import com.petstagram.dto.CreateCommentRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.Comment;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.CommentRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Override
    public CommentResponseDto create(Long userId, Long boardId, CreateCommentRequestDto dto) {
        //유효성 검사
        User user = userRepository.findByIdOrElseThrows(userId);
        Board board = boardRepository.findBoardByIdOrElseThrow(boardId);

        Comment comment = new Comment(dto, board);
        Comment savedComment = commentRepository.save(comment);

        return new CommentResponseDto(savedComment);
    }

}
