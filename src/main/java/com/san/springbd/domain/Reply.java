package com.san.springbd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Entity
@Getter
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    //== 비즈니스 로직==
    public void delete(){
        this.content="삭제된 댓글입니다.";
        this.modifyDate=LocalDateTime.now();
        this.status=BoardStatus.DELETE;
    }

    public int update(String content){
        this.content=content;
        this.modifyDate=LocalDateTime.now();
        return 1;
    }
}
