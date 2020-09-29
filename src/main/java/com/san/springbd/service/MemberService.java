package com.san.springbd.service;

import com.san.springbd.domain.member.CustomUserDetails;
import com.san.springbd.domain.member.Member;
import com.san.springbd.domain.member.MemberDto;
import com.san.springbd.domain.member.Role;
import com.san.springbd.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class MemberService implements UserDetailsService{

    private final MemberRepository memberRepository;


    /**
     * 회원가입
     */
    @Transactional
    public Long join(MemberDto memberDto){
        // 비밀번호 암호화
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));

        // 계정 등록 전 아이디 중복검사
        int findMember = validateDuplicateMember(memberDto.getLoginId());

        if(findMember>0){ // 중복아이디 있을 경우
            return 0l;
        }else{
            return memberRepository.save(memberDto.toEntity()).getId();
        }
    }

    /**
     * 중복아이디 체크
     */
    public int validateDuplicateMember(String loginId){
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);

        if(userEntityWrapper.isPresent()){ // 조회 결과가 있을 경우
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));

        CustomUserDetails userDetails=
                new CustomUserDetails(member.getLoginId(),member.getPassword(),
                                      member.getNickname(),Role.MEMBER.getValue());

        return userDetails;
    }

    @Transactional
    public void updateInfo(String loginId,String password,String nickname){
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        if(password!=""){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            member.setPassword(passwordEncoder.encode(password));
        }

        if(nickname!=""){
            member.setNickname(nickname);
        }
    }
}
