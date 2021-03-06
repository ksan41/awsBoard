package com.san.springbd.controller;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.Reply;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.ReplyQueryRepository;
import com.san.springbd.service.BoardService;
import com.san.springbd.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;
    private final MemberService memberService;


    /**
     * 게시글 상세조회 요청
     */
    @GetMapping("/user/posts/{boardId}")
    public String getOnePost(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardService.selectBoard(boardId);

        model.addAttribute("board",board);

        return "boardDetail";
    }

    /**
     * 글 작성페이지 이동
     */
    @GetMapping("/user/posts/new")
    public String postsForm(){
        return "postsForm";
    }

    /**
     * 글 작성
     */
    @PostMapping("/user/posts/new")
    public String createPost(@RequestParam("loginId") String loginId,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content){
        boardService.createPost(loginId,title,content);
        return "redirect:/";
    }

    /**
     * 글 수정페이지 이동
     */
    @GetMapping("/user/posts/{boardId}/update")
    public String updateForm(@PathVariable("boardId") Long boardId, Model model){
        Board board = boardService.findById(boardId);
        model.addAttribute("board",board);
        return "boardUpdateForm";
    }

    /**
     * 글 수정
     */
    @PostMapping("/user/posts/{boardId}/update")
    public String update(@PathVariable("boardId") Long boardId,
                         @RequestParam("title") String title,
                         @RequestParam("content") String content){
        boardService.update(boardId,title,content);
        return "redirect:/";
    }

    /**
     * 글 삭제
     */
    @PostMapping("/user/posts/{boardId}/delete")
    public String delete(@PathVariable("boardId") Long boardId){
        boardService.delete(boardId);
        return "redirect:/";
    }
}
