package com.san.springbd.repository;

import com.san.springbd.domain.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    /**
     * 게시글 등록
     */
    public void save(Board board){
        em.persist(board);
    }

    /**
     * 전체 게시글 조회
     */
    public List<Board> findAll(){
        return em.createQuery("select b from Board b order by b.createDate desc",Board.class)
                .getResultList();
    }

    /**
     * 게시글 상세조회
     */
    public Board findById(Long id) {
        return em.find(Board.class,id);
    }


}
