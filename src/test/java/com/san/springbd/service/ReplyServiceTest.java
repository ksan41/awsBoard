package com.san.springbd.service;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardStatus;
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
        assertEquals(1,boardRepository.findById(board.getId()).get().getReplyCount());
        assertEquals(content,replyRepository.findById(savedId).get().getContent());
    }

    @Test
    public void 댓글수정_테스트(){
        // given
        Reply reply = createReply();
        String newContent = "수정된 댓글입니다~";

        // when
        int result = replyService.update(reply.getId(),newContent);

        // then
        assertEquals(1,result);
        assertEquals(newContent,replyRepository.findById(reply.getId()).get().getContent());
    }

    @Test
    public void 댓글삭제_테스트(){
        // given
        Reply reply=createReply();

        // when
        replyService.deleteReply(reply.getId());

        // then
        assertEquals(BoardStatus.DELETE,replyRepository.findById(reply.getId()).get().getStatus());
        assertEquals("삭제된 댓글입니다.",replyRepository.findById(reply.getId()).get().getContent());

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
        return boardRepository.findById(savedId).get();
    }

    private Reply createReply(){
        Member member = createMember();
        Board board = createBoard();
        String content = "댓글내용~~";
        Long savedId = replyService.createReply(board.getId(),member.getLoginId(),content);
        return replyRepository.findById(savedId).get();
    }
}