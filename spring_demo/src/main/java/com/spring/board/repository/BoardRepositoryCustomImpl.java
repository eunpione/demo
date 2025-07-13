package com.spring.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import com.spring.board.dto.BoardDto;
import com.spring.board.entity.Board;
import org.springframework.stereotype.Repository;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import static com.spring.board.entity.QBoard.board;

@Repository
public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{


    @PersistenceContext
    private EntityManager em;

    // Querydsl의 쿼리 빌더 객체
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
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

        return update
                .set(board.title, dto.getTitle())
                .set(board.content, dto.getContent())
                .set(board.changedDate, LocalDateTime.now())
                .where(board.id.eq(dto.getId()))
                .execute();
    }
}
