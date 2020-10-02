package com.san.springbd.service;

import com.san.springbd.domain.member.Member;
import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void 회원가입_테스트(){
        // given
        MemberDto memberDto=new MemberDto();
        String loginId = "test01";
        String password="1234";
        String nickname="길동이";
        memberDto.setLoginId(loginId);
        memberDto.setPassword(password);
        memberDto.setNickname(nickname);

        // when
        Long savedId = memberService.join(memberDto);

        // then
        assertEquals(savedId,memberRepository.findByLoginId(loginId).get().getId());
    }

    @Test
    public void 중복아이디체크_테스트(){
        // given
        Member oldMember = createMember(); // 기존 아이디 test01
        String newLoginId = "test01";

        // when
        int result = memberService.validateDuplicateMember(newLoginId);

        // then
        assertEquals(1,result);
    }

    @Test
    public void 회원정보수정_테스트(){
        // given
        Member member = createMember();
        String loginId = member.getLoginId();
        String newPassword = "54321";
        String newNickname = "길순이";

        // when
        memberService.updateInfo(loginId,newPassword,newNickname);

        // then
        Assertions.assertTrue(new BCryptPasswordEncoder().matches(newPassword,
                              memberRepository.findByLoginId(loginId).get().getPassword()));
        assertEquals(newNickname,memberRepository.findByLoginId(loginId).get().getNickname());
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