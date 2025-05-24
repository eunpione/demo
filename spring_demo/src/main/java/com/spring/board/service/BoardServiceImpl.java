package com.spring.board.service;


import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;
import com.spring.board.entity.Board;
import com.spring.board.entity.User;
import com.spring.board.repository.BoardRepository;
import com.spring.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{


    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardDto createBoard(BoardRequestDto dto){

        User author = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("등록된 사용자가 아닙니다. 회원가입이 필요합니다."+ dto.getUserId()));

        Board board = dto.toEntity(author);
        Board savedBoard = boardRepository.save(board);

        return BoardDto.fromEntity(savedBoard);
    }

    public BoardDto getBoard(Long id){
        return null;
    }


    public BoardDto updateBoard(BoardRequestDto dto){
        return null;
    }

    public int deleteBoard(Long id){
        return 0;
    }
}
