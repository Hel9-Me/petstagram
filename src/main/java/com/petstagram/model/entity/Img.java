package com.petstagram.model.entity;

import com.petstagram.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "img")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Img{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String saved_name;

//    @Value("${img.path}")
    private String path;

    @Column(nullable = false)
    private String ext;

    @Column(nullable = false)
    private AccountStatus useyn;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
