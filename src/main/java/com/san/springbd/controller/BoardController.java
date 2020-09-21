package com.san.springbd.controller;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.member.Member;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.service.BoardService;
import com.san.springbd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final MemberService memberService;

    /**
     * 게시글 상세조회 요청
     */
    @Transactional
    @GetMapping("/user/posts/{boardId}")
    public String getOnePost(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardService.findById(boardId);
        board.addCount();
        model.addAttribute("board",board);

        return "boardDetail";
    }

    /**
     * 글 작성페이지 이동
     */
    @GetMapping("/user/posts")
    public String postsForm(){
        return "postsForm";
    }

    /**
     * 글 작성
     */
    @PostMapping("/user/posts")
    public String createPost(@RequestParam("loginId") String loginId,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content){
        boardService.createPost(loginId,title,content);
        return "redirect:/";
    }
}
