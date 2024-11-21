package com.petstagram.dto.board;

import com.petstagram.model.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/**
 * 게시글 조회 응답 DTO 클래스.
 * 특정 게시글에 대한 정보를 반환하는데 사용됩니다.
 */
@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private Long boardId;  // 게시글 ID
    private String content; // 게시글 내용
    private Timestamp updated_at; // 게시글 업데이트 시간
    private String name; // 게시글 작성자 이름
    private String email; // 게시글 작성자 이메일
    private List<String> imgs; // 게시글에 첨부된 이미지들의 목록

    /**
     * Board 엔티티 객체를 사용하여 BoardResponseDto를 생성합니다.
     *
     * @param board 게시글 엔티티 객체
     */
    public BoardResponseDto(Board board) {
        this.boardId = board.getId();
        this.content = board.getContent();
        this.updated_at = board.getUpdatedAt();
        this.name = board.getUser().getName();
        this.email = board.getUser().getEmail();
    }
}
