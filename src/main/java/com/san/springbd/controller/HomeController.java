package com.san.springbd.controller;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardSearch;
import com.san.springbd.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @RequestMapping("/")
    public String home(@RequestParam(name = "page",defaultValue = "0") int page, Model model,
                       @RequestParam(name="boardSearch",defaultValue = "") String boardSearch,
                       String keyword){
        BoardSearch rqBoardSearch;
        switch (boardSearch){
            case "nickname": rqBoardSearch = BoardSearch.NICKNAME;break;
            case "title" : rqBoardSearch = BoardSearch.TITLE;break;
            case "content" : rqBoardSearch = BoardSearch.CONTENT;break;
            default:rqBoardSearch = null;break;
        }
        PageRequest pageRequest = PageRequest.of(page,5);
       Page<Board> boards = boardService.findBoards(pageRequest,rqBoardSearch,keyword);

       model.addAttribute("boards",boards);
       model.addAttribute("maxPage",5);
       model.addAttribute("boardSearch",boardSearch);
       model.addAttribute("keyword",keyword);
       return "home";
    }
}
