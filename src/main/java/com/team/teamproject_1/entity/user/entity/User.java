package com.team.teamproject_1.entity.user.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@ToString
@Getter
@NoArgsConstructor
public class User {
    //*****추가 예정 사항(1.9)*****
    //클래스명 Users로 변경
    //loginId 삭제
    //enroll_date 추가
    //delete_date 추가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginId;
    private String password;
    private String email;
    private String username;
    //010-1234-5678
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Role role;

    public User(String loginId, String password, String email, String username, String phoneNumber, Role role) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

}
