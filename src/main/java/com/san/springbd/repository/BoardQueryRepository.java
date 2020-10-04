package com.san.springbd.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.san.springbd.domain.Board;
import com.san.springbd.domain.BoardSearch;
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

    public Page<Board> findAll(PageRequest pageRequest, BoardSearch boardSearch,String keyword){
        JPQLQuery query = queryFactory.selectFrom(board)
                          .where(board.status.eq(BoardStatus.VISIBLE),
                                  nicknameEq(boardSearch,keyword),
                                  titleEq(boardSearch,keyword),
                                  contentEq(boardSearch,keyword));

        query.orderBy(board.createDate.desc())
                .offset(pageRequest.getOffset())
                .limit(pageRequest.getPageSize());

        return new PageImpl<>(query.fetch(),pageRequest,query.fetchCount());
    }

    private BooleanExpression nicknameEq(BoardSearch boardSearch,String keyword){
        return boardSearch == BoardSearch.NICKNAME ? board.nickname.like("%"+keyword+"%") : null;
    }

    private BooleanExpression titleEq(BoardSearch boardSearch,String keyword){
        return boardSearch == BoardSearch.TITLE ? board.title.like("%"+keyword+"%") : null;
    }

    private BooleanExpression contentEq(BoardSearch boardSearch,String keyword){
        return boardSearch == BoardSearch.CONTENT ?
                board.title.like("%"+keyword+"%").or(board.content.like("%"+keyword+"%")) : null;
    }
}
