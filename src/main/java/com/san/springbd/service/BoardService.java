package com.san.springbd.service;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.member.CustomUserDetails;
import com.san.springbd.domain.member.Member;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    /**
     * 글 등록
     */
    @Transactional
    public Long createPost(String loginId,String title,String content){
        // 유저 정보 조회
        System.out.println("아이디: "+loginId);
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        Board board = Board.createBoard(member,title,content);

        boardRepository.save(board);

        return board.getId();
    }

    /**
     * 전체 게시글 조회
     */
    public List<Board> findBoards(){
        return boardRepository.findAll();
    }
}
