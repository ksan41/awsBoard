package com.san.springbd.domain;

import com.san.springbd.domain.member.Member;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Board {

    @Id
    @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    private int count; // 조회수

    @Enumerated(EnumType.STRING)
    private BoardStatus status; // 게시글 상태(VISIBLE, DELETE)

    // == 생성 메서드==//

    public static Board createBoard(Member member,String title,String content){
        Board board=new Board();
        board.member=member;
        board.title=title;
        board.content=content;
        board.status=BoardStatus.VISIBLE;
        board.createDate=LocalDateTime.now();
        board.count=0;
        return board;
    }
}
