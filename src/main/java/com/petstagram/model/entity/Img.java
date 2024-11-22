package com.petstagram.model.entity;

import com.petstagram.common.constants.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

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

    @Value("${img.path}") //todo 적용 안되는 문제 확인하기
    private String path;

    @Column(nullable = false)
    private String ext;

    @Column(nullable = false)
    private AccountStatus useyn;


    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public Img(String originalFilename) {
        String[] fileName = originalFilename.split("\\.");
        this.name = fileName[0];
        this.ext = fileName[1];
        this.useyn = AccountStatus.USE;
        this.saved_name=UUID.randomUUID().toString();
    }
}
