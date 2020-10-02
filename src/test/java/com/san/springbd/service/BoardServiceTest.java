package com.san.springbd.service;

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
}