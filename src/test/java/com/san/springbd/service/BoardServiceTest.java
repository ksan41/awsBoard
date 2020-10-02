package com.san.springbd.service;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardStatus;
import com.san.springbd.domain.member.Member;
import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;


    @Test
    public void 글등록_테스트(){
        // given
        Member member = createMember();
        String loginId = member.getLoginId();
        String title="제목입니다";
        String content="내용입니다";

        // when
        Long savedId = boardService.createPost(loginId,title,content);

        // then
        assertEquals(loginId,boardRepository.findById(savedId).getLoginId());
        assertEquals(title,boardRepository.findById(savedId).getTitle());
        assertEquals(content,boardRepository.findById(savedId).getContent());
    }


    @Test
    public void 게시글삭제_테스트(){
        // given
        Board board = createBoard();

        // when
        boardService.delete(board.getId());

        // then
        assertEquals(BoardStatus.DELETE,boardRepository.findById(board.getId()).getStatus());
    }

    @Test
    public void 게시글수정_테스트(){
        // given
        Board board = createBoard();
        Long boardId = board.getId();
        String newTitle = "새로운 제목";
        String newContent = "새로운 내용";

        // when
        boardService.update(boardId,newTitle,newContent);

        // then
        assertEquals(newTitle,boardRepository.findById(boardId).getTitle());
        assertEquals(newContent,boardRepository.findById(boardId).getContent());
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