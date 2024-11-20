package com.petstagram.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends Time{
    @Id
    @Column(name = "friend_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private User user;
}
