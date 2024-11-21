package com.petstagram.model.entity;

import com.petstagram.model.dto.CreateBoardRequestDto;
import com.petstagram.model.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "board")
public class Board extends Time{
    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private AccountStatus useyn;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Img> imgList = new ArrayList<>();

    public Board(CreateBoardRequestDto dto, User user) {
        this.content = dto.getContent();
        this.useyn = AccountStatus.Y;
        this.user = user;

    }
}
