package com.spring.board.repository;

import com.spring.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findByTitle(String searchStr);
    Optional<Board> findByTitleAndUserId(String title, Long user_id);
}
