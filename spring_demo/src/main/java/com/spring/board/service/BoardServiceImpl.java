package com.spring.board.service;


import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;
import com.spring.board.entity.Board;
import com.spring.board.entity.User;
import com.spring.board.repository.BoardRepository;
import com.spring.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository){ //누락
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public BoardDto createBoard(BoardRequestDto dto){

        User author = userRepository.findById(dto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("등록된 사용자가 아닙니다. 회원가입이 필요합니다."+ dto.getUserId()));

        Board board = dto.toEntity(author);
        Board savedBoard = boardRepository.save(board);

        return BoardDto.fromEntity(savedBoard);
    }
    @Override
    public BoardDto getBoard(Long id){

        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));
        return BoardDto.fromEntity(board);
    }

    @Override
    public BoardDto updateBoard(BoardRequestDto dto){
        Board board = boardRepository.findByTitleAndUser_Id(dto.getTitle(), dto.getUserId())
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

//        board.setTitle(dto.getTitle()); //dto에서만 setter 사용함에 따라 코드 수정 필요
//        board.setContent(dto.getContent());

        boardRepository.save(board);

        return BoardDto.fromEntity(board);
    }

    @Override
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
