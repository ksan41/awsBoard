package com.san.springbd.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.san.springbd.domain.Board;
import com.san.springbd.domain.Reply;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.ReplyQueryRepository;
import com.san.springbd.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


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
        ObjectMapper mapper = new ObjectMapper();

        if(board.getReplyCount()>0){
            String jsonData= null;
            try {
                jsonData = mapper.writeValueAsString(replyQueryRepository.findByRefBoard(boardId));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return jsonData;

        }
        return "";
    }

    /**
     * 댓글 작성
      */
    @ResponseBody
    @PostMapping("/user/reply/{boardId}/new")
    public Long createReply(@PathVariable("boardId") Long boardId,
                           String loginId, String content){
        Long result = replyService.createReply(boardId,loginId,content);
        return result;
    }


    /**
     * 댓글 수정
     */
    @ResponseBody
    @PostMapping("/user/reply/{replyId}")
    public int updateReply(@PathVariable("replyId") Long replyId,String content){
        int result=0;
        result = replyService.update(replyId,content);

        return result;
    }

    /**
     * 댓글 삭제
     */
    @ResponseBody
    @PostMapping("/user/reply/{replyId}/status")
    public String deleteReply(@PathVariable("replyId") Long replyId){
        Reply reply = replyService.deleteReply(replyId);

        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            result =  mapper.writeValueAsString(reply);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
