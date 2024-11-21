package com.petstagram.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User userFollower;

    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted = false;

    public Friend(User user, User userFollower) {
        this.user = user;
        this.userFollower = userFollower;
    }

    // 친구 요청 수락 처리
    public void accept() {
        this.isAccepted = true;
    }
}


