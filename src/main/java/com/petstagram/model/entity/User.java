package com.petstagram.model.entity;

import com.petstagram.model.entity.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 사용자(User) 엔티티.
 * 이 클래스는 사용자 정보를 저장하며, 사용자 계정 상태 및 작성한 게시글과 친구 목록을 관리합니다.
 */
@Entity
@Getter
@Table(name = "user")  // 데이터베이스 테이블과 매핑된 엔티티
@NoArgsConstructor(access = AccessLevel.PROTECTED)  // 기본 생성자는 외부에서 호출할 수 없도록 제한
public class User extends Time {  // Time 클래스를 상속받아 생성 시간 및 수정 시간을 처리

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")  // 유저 ID (자동 생성)
    private Long id;  // 사용자 고유 ID

    @Column(nullable = false)
    private String name;  // 사용자 이름

    @Column(nullable = false, unique = true)
    private String email;  // 사용자 이메일 (고유한 값이어야 함)

    @Column(nullable = false)
    private String password;  // 사용자 비밀번호 (암호화하여 저장)

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)  // Enum을 DB에 문자열 형태로 저장
    private AccountStatus useyn;  // 계정 상태 (활성화 또는 비활성화)

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)  // 사용자가 작성한 게시글 목록
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)  // 사용자의 친구 목록
    private List<Friend> friendList = new ArrayList<>();

    /**
     * 사용자 생성자.
     * 사용자를 생성할 때 계정 상태를 설정하여 활성화된 사용자로 생성합니다.
     *
     * @param name 사용자 이름
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @param useyn 계정 활성화 여부
     */
    public User(String name, String email, String password, AccountStatus useyn) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.useyn = useyn;
    }

    /**
     * 계정 비활성화 메서드.
     * 계정 상태를 변경하여 비활성화된 사용자로 설정합니다.
     *
     * @param accountStatus 변경할 계정 상태 (활성화 또는 비활성화)
     */
    public void disableUserAccount(AccountStatus accountStatus) {
        this.useyn = accountStatus;  // 계정 상태를 비활성화로 설정
    }
}
