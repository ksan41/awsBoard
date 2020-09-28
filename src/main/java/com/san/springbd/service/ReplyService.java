package com.san.springbd.service;

import com.san.springbd.domain.Board;
import com.san.springbd.domain.Reply;
import com.san.springbd.domain.member.Member;
import com.san.springbd.repository.BoardRepository;
import com.san.springbd.repository.MemberRepository;
import com.san.springbd.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReplyService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final ReplyRepository replyRepository;

    /**
     * 댓글 작성
     */
    @Transactional
    public int createReply(Long boardId, String loginId,String content){
        int result = 0;
        // 유저 정보 조회
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        Board board =  boardRepository.findById(boardId);

        Reply reply = Reply.createReply(member.getLoginId(),member.getNickname(),boardId,content);
        replyRepository.save(reply);

        if(reply!=null){
         result=1;
         board.addReplyCount();
        }
        return result;
    }

    /**
     * 댓글 수정
     */
    @Transactional
    public int update(Long replyId,String content){
        Optional<Reply> replyEntityWrapper = replyRepository.findById(replyId);
        Reply reply = replyEntityWrapper.get();
        int result = reply.update(content);

        return result;
    }


    /**
     * 댓글 삭제
     */
    @Transactional
    public Reply deleteReply(Long replyId){
        Optional<Reply> replyEntityWrapper = replyRepository.findById(replyId);
        Reply reply = replyEntityWrapper.get();
        reply.delete();

        return reply;
    }

}
