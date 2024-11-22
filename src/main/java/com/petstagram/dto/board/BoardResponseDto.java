package com.petstagram.dto.board;

import com.petstagram.model.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Getter
public class BoardResponseDto {
    Long boardId;
    String content;
    Timestamp updated_at;
    String name;
    String email;
    List<ImgResponseDto> imgList;


    public BoardResponseDto(Board board) {
        this.boardId = board.getId();
        this.content = board.getContent();
        this.updated_at = board.getUpdatedAt();
        this.name = board.getUser().getName();
        this.email = board.getUser().getEmail();
        this.imgList = board.getImgList().stream()
                .map(m->new ImgResponseDto(m))
                .toList();
    }
}
