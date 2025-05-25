package com.spring.board.service;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;

public interface BoardService {

    BoardDto createBoard(BoardRequestDto dto);
    BoardDto getBoard(Long id);
    BoardDto updateBoard(BoardRequestDto dto);
    int deleteBoard(Long id);
}
