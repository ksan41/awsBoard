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

    private int replyCount; // 댓글 개수

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

    // == 비즈니스 로직== //

    /**
     * 조회수 증가
     */
    public void addCount(){
        this.count+=1;
    }

    /**
     * 게시글 삭제처리
     */
    public void delete(){
        this.status=BoardStatus.DELETE;
    }

    /**
     * 게시글 수정
     */
    public void update(String title,String content){
        this.title=title;
        this.content=content;
        this.modifyDate=LocalDateTime.now();
    }

    /**
     * 댓글 개수 증가
     */
    public void addReplyCount(){
        this.replyCount+=1;
    }

    /**
     * 댓글 개수 감소
     */
    public void subReplyCount(){
        this.replyCount-=1;
    }
}
