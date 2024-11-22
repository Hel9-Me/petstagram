package com.petstagram.dto.comment;

import com.petstagram.model.entity.Comment;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class CommentResponseDto {
    private Long comment_id;
    private Long board_id;
    private Long user_id;
    private String comment;
    private Timestamp updated_date;

    public CommentResponseDto(Comment comment) {
        this.comment_id = comment.getId();
        this.board_id = comment.getBoard().getId();
        this.user_id = comment.getUser().getId();
        this.comment = comment.getComment();
        this.updated_date = comment.getUpdatedAt();
    }
}
