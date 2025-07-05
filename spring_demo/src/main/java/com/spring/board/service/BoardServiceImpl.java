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
import java.util.List;
import java.util.stream.Collectors;

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


    public List<BoardDto> getAllBoards(){
        List<Board> allBoards = boardRepository.findAll();

        if(allBoards.isEmpty()){
            throw new IllegalArgumentException("작성된 게시글이 없습니다.");
        }
//        return allBoards.stream().map(board -> BoardDto.fromEntity(board)).collect(Collectors.toList());
        return allBoards.stream().map(BoardDto::fromEntity).collect(Collectors.toList());
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

        try{
            board.update(dto.getTitle(), dto.getContent());
            return BoardDto.fromEntity(board);
        }catch(IllegalArgumentException e){
            throw new RuntimeException("게시글 수정 실패: "+ e.getMessage());
        }catch (Exception e) {
            throw new RuntimeException("알 수 없는 오류로 게시글 수정 실패", e);
        }
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
