package com.san.springbd.controller;

import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

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
    public String join(MemberDto memberDto){
        memberService.join(memberDto);
        return "redirect:/user/login";
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
    @GetMapping("/user/mypage")
    public String mypage(){
        return "/mypage";
    }
}
