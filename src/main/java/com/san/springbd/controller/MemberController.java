package com.san.springbd.controller;

import com.san.springbd.domain.member.Member;
import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.repository.MemberRepository;
import com.san.springbd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    /**
     * 회원가입 페이지로 이동
     */
    @GetMapping("/user/join")
    public String joinForm(){
        return "/joinForm";
    }

    /**
     * 회원가입
     */
    @PostMapping("/user/join")
    public String join(MemberDto memberDto,Model model){
        Long result = memberService.join(memberDto);

        if(result!=0l){ // 정상적으로 가입 되었을 경우
            return "redirect:/user/login";
        }else{ // 중복아이디 있을경우
            model.addAttribute("message","이미 존재하는 아이디입니다.");
            return "/joinForm";
        }
    }

    /**
     * 로그인 폼
     */
    @GetMapping("/user/login")
    public String loginForm(){
        return "/loginForm";
    }

    /**
     * 마이페이지
     */
    @GetMapping("/user/mypage/{loginId}")
    public String myPage(@PathVariable("loginId") String loginId, Model model){
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        model.addAttribute("member",member);
        return "myPage";
    }

    /**
     * 회원정보 수정
     */
    @PostMapping("/user/mypage/{loginId}")
    public String updateMember(@PathVariable("loginId") String loginId,String password,String nickname){
        memberService.updateInfo(loginId,password,nickname);

        return "redirect:/user/mypage/"+loginId;
    }
}
