package com.petstagram.service.imp;

import com.petstagram.common.constants.BoardErrorCode;
import com.petstagram.common.constants.CommentErrorCode;
import com.petstagram.common.constants.UserErrorCode;
import com.petstagram.common.exception.CustomException;
import com.petstagram.dto.comment.CommentRequestDto;
import com.petstagram.dto.comment.CommentResponseDto;
import com.petstagram.dto.comment.CreateCommentRequestDto;
import com.petstagram.dto.comment.DeleteCommentRequestDto;
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
        //todo 게시물 작성자, 댓글 작성자 비밀번호 확인
        validateAccessUser(userId,dto.getPassword(), comment);

        comment.update(dto);

        return new CommentResponseDto(comment);
    }

    @Override
    public void delete(Long userId, Long commentId, DeleteCommentRequestDto dto) {
        Comment comment = commentRepository.findByIdOrElseThrows(commentId);
        validateAccessUser(userId,dto.getPassword(),comment);
        commentRepository.delete(comment);
    }

    private static void validateAccessUser(Long userId, String password, Comment comment) {
        User commentWriter = comment.getUser();
        User boardWriter = comment.getBoard().getUser();
        if (!PasswordEncoder.matches(password, commentWriter.getPassword())||!PasswordEncoder.matches(password,boardWriter.getPassword())) {
            throw new CustomException(UserErrorCode.PASSWORD_INCORRECT);
        }
        if (!userId.equals(commentWriter.getId()) || !userId.equals(boardWriter.getId())) {
            throw new CustomException(CommentErrorCode.INVALID_ACCESS);
        }
    }
}
