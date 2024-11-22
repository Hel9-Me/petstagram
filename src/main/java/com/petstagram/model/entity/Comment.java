package com.petstagram.model.entity;

import com.petstagram.common.constants.AccountStatus;
import com.petstagram.dto.CreateCommentRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;
    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String useyn;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Comment(CreateCommentRequestDto dto,Board board) {
        this.comment = dto.getComment();
        this.useyn = AccountStatus.USE.toString();
        this.board = board;
    }
}
