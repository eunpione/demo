package com.spring.board.controller;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;
import com.spring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시글 등록
    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardRequestDto dto){
        BoardDto createdBoard = boardService.createBoard(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    //게시글 조회


    //게시글 삭제


    //게시글 수정


}