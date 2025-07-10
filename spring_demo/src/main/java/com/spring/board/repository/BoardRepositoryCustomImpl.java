package com.spring.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.spring.board.dto.BoardDto;
import com.spring.board.entity.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.spring.board.entity.QBoard.board;

@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public BoardRepositoryCustomImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Board> findByTitleContaining(String searchStr){
        return queryFactory
                .selectFrom(board)
                .where(board.title.contains(searchStr))
                .orderBy(board.createdDate.desc())
                .fetch();
    }

    @Override
    public long updateTitleAndContent(BoardDto dto){
        JPAUpdateClause update = new JPAUpdateClause(em, board);

        long row = update
                .set(board.title, dto.getTitle())
                .set(board.content, dto.getContent())
                .where(board.id.eq(dto.getId()))
                .execute();

        return row;
    }
}
