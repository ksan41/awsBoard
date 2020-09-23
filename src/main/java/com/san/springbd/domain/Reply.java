package com.san.springbd.domain;

import com.san.springbd.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="reply")
public class Reply {

    @Id
    @GeneratedValue
    @Column(name="reply_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private Long refBoard;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @Enumerated(EnumType.STRING)
    private BoardStatus status;

    @Builder
    public Reply(Long memberId, Long refBoard, String content,LocalDateTime createDate,
                 BoardStatus status){
        this.memberId=memberId;
        this.refBoard=refBoard;
        this.content=content;
        this.createDate=LocalDateTime.now();
        this.status=BoardStatus.VISIBLE;
    }
}
