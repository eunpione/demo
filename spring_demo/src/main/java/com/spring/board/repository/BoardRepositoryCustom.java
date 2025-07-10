package com.spring.board.repository;

import com.spring.board.dto.BoardDto;
import com.spring.board.entity.Board;
import java.util.List;

public interface BoardRepositoryCustom {

    List<Board> findByTitleContaining(String searchStr);

    long updateTitleAndContent(BoardDto dto);



}
