package com.san.springbd.controller;

import com.san.springbd.domain.Board;
import com.san.springbd.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardService boardService;

    @RequestMapping("/")
    public String home(@RequestParam(name = "page",defaultValue = "0") int page, Model model){
        PageRequest pageRequest = PageRequest.of(page,5,Sort.by("createDate").descending());
       Page<Board> boards = boardService.findBoards(pageRequest);

       model.addAttribute("boards",boards);
       model.addAttribute("maxPage",5);
       return "home";
    }
}
