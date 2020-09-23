package com.san.springbd.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.san.springbd.domain.Reply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.san.springbd.domain.QReply.reply;

@RequiredArgsConstructor
@Repository
public class ReplyQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<Reply> findByRefBoard(Long refBoard){
        return queryFactory.selectFrom(reply)
                .where(reply.refBoard.eq(refBoard))
                .orderBy(reply.createDate.desc())
                .fetch();
    }
}
