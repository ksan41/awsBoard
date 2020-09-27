package com.san.springbd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.san.springbd.domain.Board;
import com.san.springbd.domain.Reply;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.ReplyQueryRepository;
import com.san.springbd.service.BoardService;
import com.san.springbd.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyQueryRepository replyQueryRepository;
    private final ReplyService replyService;
    private final BoardRepository boardRepository;

    /**
     * 게시글 댓글 불러오기
     */
    @GetMapping(value = "/user/reply/{boardId}",produces = "application/json; charset=utf-8")
    public @ResponseBody String getAllReplies(@PathVariable("boardId") Long boardId){
        Board board = boardRepository.findById(boardId);

        if(board.getReplyCount()>0){
                String jsonData= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(replyQueryRepository.findByRefBoard(boardId));
                return jsonData;

        }
        return "";
    }

    /**
     * 댓글 작성
      */
    @ResponseBody
    @PostMapping("/user/reply/{boardId}")
    public int createReply(@PathVariable("boardId") Long boardId,
                           String loginId, String content){
        int result = replyService.createReply(boardId,loginId,content);
        return result;
    }


    /**
     * 댓글 수정
     */

    /**
     * 댓글 삭제
     */
}
