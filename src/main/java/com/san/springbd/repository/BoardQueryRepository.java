package com.san.springbd.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.san.springbd.domain.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Board> findAll(){
        return queryFactory.selectFrom(board)
                .where(board.status.eq(BoardStatus.VISIBLE))
                .orderBy(board.createDate.desc())
                .fetch();
    }
}
