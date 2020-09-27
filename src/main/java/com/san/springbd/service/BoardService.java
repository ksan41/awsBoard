package com.san.springbd.service;

import com.san.springbd.domain.Board;
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
        Optional<Member> userEntityWrapper = memberRepository.findByLoginId(loginId);
        Member member = userEntityWrapper.get();

        Board board = Board.createBoard(member,title,content);

        boardRepository.save(board);

        return board.getId();
    }

    /**
     * 게시글 조회
     */
    public Board findById(Long boardId){
        return boardRepository.findById(boardId);
    }

    /**
     * 게시글 상세조회(조회수 증가)
     */
    @Transactional
    public Board selectBoard(Long boardId){
        Board board = findById(boardId);
        board.addCount();
        return board;
    }


    /**
     * 전체 게시글 조회
     */
    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    /**
     * 게시글 삭제
     */
    @Transactional
    public void delete(Long boardId){
        Board board = findById(boardId);
        board.delete();
    }

    /**
     * 게시글 수정
     */
    @Transactional
    public void update(Long boardId,String title,String content){
        Board board = findById(boardId);
        board.update(title,content);
    }
}
