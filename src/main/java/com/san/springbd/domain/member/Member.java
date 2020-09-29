package com.san.springbd.domain.member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    @Column(length = 15,nullable = false,unique = true)
    private String loginId;

    @Column(length = 8,nullable = false)
    private String nickname;

    @Column(length = 100,nullable = false)
    private String password;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    @Builder
    public Member(Long id, String loginId, String nickname, String password){
        this.id=id;
        this.loginId=loginId;
        this.nickname=nickname;
        this.password=password;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public void setNickname(String nickname){
        this.nickname=nickname;
    }
}
