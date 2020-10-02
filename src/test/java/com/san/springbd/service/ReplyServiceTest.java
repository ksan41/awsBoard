package com.san.springbd.service;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.Reply;
import com.san.springbd.domain.member.Member;
import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.MemberRepository;
import com.san.springbd.repository.ReplyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReplyServiceTest {

    @Autowired
    private ReplyService replyService;
    @Autowired
    private ReplyRepository replyRepository;

    @Autowired private MemberService memberService;
    @Autowired private MemberRepository memberRepository;
    @Autowired private BoardService boardService;
    @Autowired private BoardRepository boardRepository;

    @Test
    public void 댓글작성_테스트(){
        // given
        Board board = createBoard();
        Member member=memberRepository.findByLoginId("test01").get();
        String content = "댓글내용~~";

        // when
        Long savedId = replyService.createReply(board.getId(),member.getLoginId(),content);

        // then
        assertEquals(1,boardRepository.findById(board.getId()).getReplyCount());
        assertEquals(content,replyRepository.findById(savedId).get().getContent());
    }


    // 테스트용 Member 데이터
    private Member createMember(){
        MemberDto memberDto=new MemberDto();
        String loginId = "test01";
        String password="1234";
        String nickname="길동이";
        memberDto.setLoginId(loginId);
        memberDto.setPassword(password);
        memberDto.setNickname(nickname);

        memberService.join(memberDto);

        return memberRepository.findByLoginId(loginId).get();
    }

    // 테스트용 Board 데이터
    private Board createBoard(){
        Member member = createMember();
        String loginId = member.getLoginId();
        String title="제목입니다";
        String content="내용입니다";
        Long savedId = boardService.createPost(loginId,title,content);
        return boardRepository.findById(savedId);
    }
}