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
import java.util.Optional;
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
    public void updateBoard(BoardDto dto){
        Board board = boardRepository.findById(dto.getId())
                .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다."));

//        [공유3] 작성자와 로그인 사용자 동일 여부 검증 방식은 클라이언트에서 바로 받는 것이 부적절

//        Optional<User> authorUser = userRepository.findByUsername(dto.getAuthorUsername());
//
//        if(authorUser.isPresent() && !authorUser.get().getId().equals(loginUserId)){
//            throw new IllegalArgumentException("게시글 작성자가 아닙니다.");
//        }

        BoardDto dtoToUpdate = BoardDto.fromEntity(board);
        dtoToUpdate.setTitle(dto.getTitle());
        dtoToUpdate.setContent(dto.getContent());

//        [공유1] Setter의 제한적 사용
//        board.setTitle(dto.getTitle()); //dto에서만 setter 사용함에 따라 코드 수정 필요
//        board.setContent(dto.getContent());

//        [공유2] 객체지향적 코드 -> 객체에서 책임지도록 하려다가 이슈 발생
//        try{
//            board.update(dto.getTitle(), dto.getContent());
//            return BoardDto.fromEntity(board);
//        }catch(IllegalArgumentException e){
//            throw new RuntimeException("게시글 수정 실패: "+ e.getMessage());
//        }catch (Exception e) {
//            throw new RuntimeException("알 수 없는 오류로 게시글 수정 실패", e);


        long count = boardRepository.updateTitleAndContent(dtoToUpdate);
        if (count != 1) {
            throw new IllegalArgumentException("게시글 수정에 실패했습니다. id=" + dto.getId());
        }

    }

    @Override
    @Transactional
    public int deleteBoard(Long id){

        Optional<Board> BoardToDelete = boardRepository.findById(id);

        if (BoardToDelete.isPresent()) {
            boardRepository.updateBoardByDeleteYn(id);  // deleteYn = true
            return 1;
        } else {
            return 0;
        }
    }
}
