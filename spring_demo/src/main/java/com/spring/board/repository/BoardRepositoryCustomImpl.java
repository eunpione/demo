package com.spring.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.board.entity.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.spring.board.entity.QBoard.board;

@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BoardRepositoryCustomImpl(JPAQueryFactory queryFactory){
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Board> findByTitleContaining(String searchStr){
        return queryFactory
                .selectFrom(board)
                .where(board.title.contains(searchStr))
                .orderBy(board.createdDate.desc())
                .fetch();
    }
}
