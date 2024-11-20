package com.petstagram.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Table(name = "img")
@Entity
public class Img{
    @Id
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
    @JoinColumn(name = "id")
    private Board board;
}
