package com.spring.board.repository;

import com.spring.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {

    @Modifying
    @Query("UPDATE Board b SET b.deleteYn = true WHERE b.id = :boardId")
    void updateBoardByDeleteYn(@Param("boardId") Long boardId);

}
