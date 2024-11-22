package com.petstagram.service.imp;

import com.petstagram.dto.like.LikeRequestDto;
import com.petstagram.dto.like.LikeResponseDto;
import com.petstagram.model.entity.Board;
import com.petstagram.model.entity.Comment;
import com.petstagram.model.entity.Like;
import com.petstagram.model.entity.User;
import com.petstagram.repository.BoardRepository;
import com.petstagram.repository.CommentRepository;
import com.petstagram.repository.LikeRepository;
import com.petstagram.repository.UserRepository;
import com.petstagram.service.LikeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Transactional
    @Override
    public LikeResponseDto likePost(LikeRequestDto dto) {
        // userId가 null인지 확인
        if (dto.getUserId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "UserId는 필수 항목입니다.");
        }

        // boardId 또는 commentId가 null인지 확인
        if (dto.getBoardId() == null && dto.getCommentId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "게시글이나 댓글 ID는 필수 항목입니다.");
        }

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."));

        Board board = null;
        Comment comment = null;

        // boardId와 commentId가 모두 제공될 수 있으므로, 둘 중 하나를 선택적으로 처리
        if (dto.getBoardId() != null) {
            board = boardRepository.findById(dto.getBoardId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        }

        if (dto.getCommentId() != null) {
            comment = commentRepository.findById(dto.getCommentId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        }

        // 좋아요가 이미 존재하는지 확인 (게시글이나 댓글 둘 중 하나에 좋아요가 이미 눌렀는지 체크)
        if (likeRepository.findByUserIdAndBoardIdAndCommentId(dto.getUserId(), dto.getBoardId(), dto.getCommentId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 좋아요를 눌렀습니다.");
        }

        Like like = new Like(user, board, comment);
        Like savedLike = likeRepository.save(like);

        return new LikeResponseDto(savedLike.getId(), user.getId(),
                board != null ? board.getId() : null,
                comment != null ? comment.getId() : null);
    }


    @Transactional
    @Override
    public void unlikePost(LikeRequestDto dto) {
        Like like = likeRepository.findByUserIdAndBoardIdAndCommentId(dto.getUserId(), dto.getBoardId(), dto.getCommentId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "좋아요 기록이 존재하지 않습니다."));

        likeRepository.delete(like);
    }
}

