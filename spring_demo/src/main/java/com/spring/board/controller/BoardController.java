package com.spring.board.controller;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;
import com.spring.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class BoardController {

    //private final BoardService boardService;
    // 아래 직접 생성자 주입 방식 대신 위와 같이 final로 생성하고 
    // RequiredArgsConstructor 어노테이션을 설정하여 자동 생성자 주입 가능
    private final BoardService boardService;

//    public BoardController(BoardService boardService){
//        this.boardService = boardService;
//    }

    //게시글 등록
    @PostMapping("/board")
    public ResponseEntity<BoardDto> createBoard(@RequestBody BoardRequestDto dto){
        BoardDto createdBoard = boardService.createBoard(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBoard);
    }

    //게시글 조회
    @GetMapping("/board/{boardId}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long boardId){
        BoardDto getBoard = boardService.getBoard(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(getBoard);
    }

    //게시글 전체 조회
    @GetMapping("/board/")
    public ResponseEntity<List<BoardDto>> getAllBoards(){
        List<BoardDto> allBoards = boardService.getAllBoards();
        return ResponseEntity.status(HttpStatus.OK).body(allBoards);
    }

    //게시글 삭제
    @DeleteMapping("/board/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardId){
        int result = boardService.deleteBoard(boardId);
        if(result == 1){
            return ResponseEntity.status(HttpStatus.OK).body("게시글이 삭제되었습니다.");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("삭제할 게시글이 존재하지 않습니다.");
        }
    }

    //게시글 수정
    @PutMapping("/board")
    public ResponseEntity<BoardDto> updateBoard(@RequestBody BoardRequestDto boardRequestDto){
        BoardDto updatedBoard = boardService.updateBoard(boardRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updatedBoard);
    }


}