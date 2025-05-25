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

        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return BoardDto.fromEntity(board);
    }


    public BoardDto updateBoard(BoardRequestDto dto){
        Board board = boardRepository.findByTitleAndUserId(dto.getTitle(), dto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

        board.setTitle(dto.getTitle());
        board.setContent(dto.getContent());

        boardRepository.save(board);

        return BoardDto.fromEntity(board);
    }

    public int deleteBoard(Long id){

        try {
            Board boardToDelete = boardRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("삭제하고자 하는 게시글이 존재하지 않습니다."));

            boardRepository.delete(boardToDelete);
            return 1;

        }catch(IllegalArgumentException e){
            return 0;
        }
    }
}
