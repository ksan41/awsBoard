package com.san.springbd.domain;

import com.san.springbd.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue
    @Column(name="reply_id")
    private Long id;

    private String loginId;

    private String nickname;

    private Long refBoard;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;


    //==생성 메소드==
    public static Reply createReply(String loginId,String nickname, Long refBoard, String content){
        Reply reply=new Reply();
        reply.loginId= loginId;
        reply.nickname=nickname;
        reply.refBoard=refBoard;
        reply.content=content;
        reply.createDate=LocalDateTime.now();
        reply.status=BoardStatus.VISIBLE;
        return reply;
    }
}
