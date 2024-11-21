package com.petstagram.model.entity;

import com.petstagram.dto.board.CreateBoardRequestDto;
import com.petstagram.model.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 게시판(Board) 엔티티.
 * 게시판에 해당하는 데이터를 저장하는 엔티티로, 게시글 내용과 작성자(User), 관련 이미지들을 포함함.
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends Time {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 게시판 ID

    @Column(nullable = false)
    private String content;  // 게시글 내용

    @Column(nullable = false)
    private AccountStatus useyn;  // 게시글 활성화 여부 (Y: 활성화, N: 비활성화)

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;  // 게시글 작성자 (User 엔티티와 연관)

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Img> imgList = new ArrayList<>();  // 게시글에 포함된 이미지들 (Img 엔티티와 연관)

    /**
     * 게시글을 생성하는 생성자.
     *
     * @param dto 게시글 생성 요청 DTO
     * @param user 게시글 작성자
     */
    public Board(CreateBoardRequestDto dto, User user) {
        this.content = dto.getContent();  // 게시글 내용 설정
        this.useyn = AccountStatus.Y;  // 게시글 활성화 상태 설정 (기본값: 활성화)
        this.user = user;  // 작성자 설정
    }
}
