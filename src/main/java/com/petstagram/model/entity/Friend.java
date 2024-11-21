package com.petstagram.model.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 친구 관계(Friend) 엔티티.
 * 두 사용자 간의 친구 요청 및 수락 상태를 관리하는 엔티티.
 */
@Entity
@Getter
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend extends Time {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 친구 관계 ID

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // 친구 요청을 보낸 사용자 (User 엔티티와 연관)

    @ManyToOne
    @JoinColumn(name = "friend_id", nullable = false)
    private User userFollower;  // 친구 요청을 받은 사용자 (User 엔티티와 연관)

    @Column(name = "is_accepted", nullable = false)
    private boolean isAccepted = false;  // 친구 요청 수락 여부 (기본값: 미수락)

    /**
     * 친구 관계를 생성하는 생성자.
     *
     * @param user 친구 요청을 보낸 사용자
     * @param userFollower 친구 요청을 받은 사용자
     */
    public Friend(User user, User userFollower) {
        this.user = user;
        this.userFollower = userFollower;
    }

    /**
     * 친구 요청을 수락 처리하는 메소드.
     */
    public void accept() {
        this.isAccepted = true;  // 친구 요청 수락
    }
}
