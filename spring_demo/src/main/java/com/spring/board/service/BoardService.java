package com.spring.board.service;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;

import java.util.List;

public interface BoardService {

    List<BoardDto> getAllBoards();
    BoardDto createBoard(BoardRequestDto dto);
    BoardDto getBoard(Long id);
    void updateBoard(BoardDto dto);
    int deleteBoard(Long id);
}
