package com.petstagram.model.entity;

import com.petstagram.model.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이미지(Img) 엔티티.
 * 게시글에 첨부된 이미지를 관리하는 엔티티.
 */
@Entity
@Getter
@Table(name = "img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;  // 이미지 ID

    @Column(nullable = false)
    private String name;  // 이미지 원본 파일 이름

    @Column(nullable = false)
    private String saved_name;  // 저장된 이미지 파일 이름

    @Column(nullable = false)
    private String path;  // 이미지 저장 경로

    @Column(nullable = false)
    private String ext;  // 이미지 파일 확장자

    @Column(nullable = false)
    private AccountStatus useyn;  // 이미지 활성화 여부 (Y: 활성화, N: 비활성화)

    @Lob
    @Column(name = "img_data", length = 100)
    private byte[] imgData;  // 이미지 데이터 (바이트 배열)

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;  // 해당 이미지가 속한 게시글 (Board 엔티티와 연관)
}
