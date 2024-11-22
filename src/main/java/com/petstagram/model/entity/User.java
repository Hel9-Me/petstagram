package com.petstagram.model.entity;

import com.petstagram.common.constants.AccountStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Time{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus useyn;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Friend> friendList = new ArrayList<>();

    public User(String name, String email, String password, AccountStatus useyn) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.useyn = useyn;
    }

    public void disableUserAccount(AccountStatus accountStatus) {
        this.useyn = accountStatus;
    }

    public void update(String newName, String newPassword) {
        this.name = newName;
        this.password = newPassword;
    }
}
