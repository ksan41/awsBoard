package com.san.springbd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.san.springbd.domain.member.Member;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createDate;

    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
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
