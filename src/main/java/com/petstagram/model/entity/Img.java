package com.petstagram.model.entity;

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
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String saved_name;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String ext;

    @Column(nullable = false)
    private Character useyn;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
