package com.san.springbd.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;


import static com.san.springbd.domain.QBoard.board;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {
    private final JPAQueryFactory queryFactory;

    public Page<Board> findAll(PageRequest pageRequest){
        QueryResults<Board> result =
                 queryFactory.selectFrom(board)
                .where(board.status.eq(BoardStatus.VISIBLE))
                .orderBy(board.createDate.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize())
                .fetchResults();
        return new PageImpl<>(result.getResults(),pageRequest,result.getTotal());
    }
}
