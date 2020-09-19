package com.san.springbd.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Member {

    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String loginId;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private Role role;

}
