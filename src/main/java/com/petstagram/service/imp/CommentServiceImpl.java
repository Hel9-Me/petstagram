package com.petstagram.service.imp;

import com.petstagram.common.constants.BoardErrorCode;
import com.petstagram.common.constants.CommentErrorCode;
import com.petstagram.common.constants.UserErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.dto.comment.CommentRequestDto;
import com.petstagram.dto.comment.CommentResponseDto;
import com.petstagram.dto.comment.CreateCommentRequestDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.Comment;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.CommentRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.CommentService;
import com.petstagram.util.PasswordEncoder;
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

    @Override
    public List<CommentResponseDto> find(Long userId, Long boardId) {
        //유효성 검사
        userRepository.findByIdOrElseThrows(userId);
        List<Comment> commentByBoardId = commentRepository.findByBoard_Id(boardId);
        if(commentByBoardId.isEmpty()) {
            throw new CustomException(BoardErrorCode.NOT_FOUND);
        }

        return commentByBoardId.stream()
                .map(m -> new CommentResponseDto(m))
                .toList();

    }

    @Override
    public CommentResponseDto update(Long userId, Long commentId, CommentRequestDto dto) {
        //유효성 검사

        Comment comment = commentRepository.findByIdOrElseThrows(commentId);
        User user = comment.getUser();
        if (!PasswordEncoder.matches(dto.getPassword(),user.getPassword())) {
            throw new CustomException(UserErrorCode.PASSWORD_INCORRECT);
        }
        if (!userId.equals(user.getId()) || !userId.equals(comment.getBoard().getId())) {
            throw new CustomException(CommentErrorCode.INVALID_ACCESS);
        }

        comment.update(dto);

        return new CommentResponseDto(comment);
    }
}
