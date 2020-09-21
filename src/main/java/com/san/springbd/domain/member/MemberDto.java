package com.san.springbd.domain.member;

import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@ToString
@NoArgsConstructor
public class MemberDto {
    private Long id;
    private String loginId;
    private String nickname;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    public Member toEntity(){
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .nickname(nickname)
                .password(password)
                .build();
    }

    @Builder
    public MemberDto(Long id, String loginId,String nickname, String password){
        this.id=id;
        this.loginId=loginId;
        this.nickname=nickname;
        this.password=password;
    }
}
