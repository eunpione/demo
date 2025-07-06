package com.spring.board.service;

import com.spring.board.dto.BoardDto;
import com.spring.board.dto.BoardRequestDto;
import com.spring.board.entity.Board;
import com.spring.board.entity.User;
import com.spring.board.repository.BoardRepository;
import com.spring.board.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest {

    @InjectMocks //테스트 대상 클래스
    private BoardServiceImpl boardServiceimpl;

    @Mock //가짜 의존성 생성
    private UserRepository userRepository;

    @Mock //가짜 의존성 생성
    private BoardRepository boardRepository;

    @Test
    void createBoard() {

        //given
        Long userId = 1L;
        String title = "테스트 제목";
        String content = "테스트 내용";

        BoardRequestDto mockBoardRequestDto = BoardRequestDto.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();

        User mockUser = User.builder()
                .id(userId)
                .role("user")
                .username("테스트")
                .email("mock@wj.com")
                .password("1234")
                .createdDate(LocalDateTime.now())
                .build();

        // supposed to be the result
        Board expectedMockBoard = Board.builder()
                .id(1L)
                .title(title)
                .content(content)
                .user(mockUser)
                .createdDate(LocalDateTime.now())
                .build();

        // Mockito의 stubbing 기능을 사용해서 가짜 반환 값을 세팅 -
        when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));
        when(boardRepository.save(any(Board.class))).thenReturn(expectedMockBoard);

        //when
        BoardDto result = boardServiceimpl.createBoard(mockBoardRequestDto);

        //then -- 즉 리턴 필요 값과 실제 저장 값을 비교
        assertNotNull(result);
        assertEquals(title, result.getTitle());
        assertEquals(content, result.getContent());
        assertEquals(mockUser.getUsername(), result.getAuthorUsername());
        verify(userRepository).findById(userId);
        verify(boardRepository).save(any(Board.class));

    }

    @Test
    void getAllBoards(){
        //given
        List<Board> mockBoardList = List.of(
                Board.builder()
                        .id(1L)
                        .user(User.builder().username("user1").build())
                        .title("test1")
                        .content("testC1")
                        .createdDate(LocalDateTime.now())
                        .build(),
                Board.builder()
                        .id(2L)
                        .user(User.builder().username("user2").build())
                        .title("test2")
                        .content("testC2")
                        .createdDate(LocalDateTime.now())
                        .build()
        );

        when(boardRepository.findAll()).thenReturn(mockBoardList);

        //when
        List<BoardDto> result = boardServiceimpl.getAllBoards();

        //then
        assertNotNull(result);

        assertEquals("test1", result.get(0).getTitle());
        assertEquals("user1", result.get(0).getAuthorUsername());
        assertEquals("testC1", result.get(0).getContent());

        assertEquals("test2", result.get(1).getTitle());
        assertEquals("user2", result.get(1).getAuthorUsername());
        assertEquals("testC2", result.get(1).getContent());

        verify(boardRepository).findAll(); // 실제 호출했는지 확인

    }

    @Test
    void getBoard() {

        //given
        List<Board> mockBoardList = List.of(
                Board.builder()
                        .id(1L)
                        .user(User.builder().username("user1").build())
                        .title("test1")
                        .content("testC1")
                        .createdDate(LocalDateTime.now())
                        .build(),
                Board.builder()
                        .id(2L)
                        .user(User.builder().username("user2").build())
                        .title("test2")
                        .content("testC2")
                        .createdDate(LocalDateTime.now())
                        .build()
        );

        Long boardId = 2L;

        Board mockBoard = mockBoardList.get(1);
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(mockBoard));

        //when
        BoardDto result = boardServiceimpl.getBoard(boardId);

        //then
        assertNotNull(result);
        assertEquals("test2", result.getTitle());
        assertEquals("testC2", result.getContent());

        verify(boardRepository).findById(boardId); // 실제 호출했는지 확인
    }

//    @Test
//    void updateBoard() {
//    }
//

    @Test
    void deleteBoard_success() {

        //given
        Long boardId = 1L;
        Board boardToDelete = Board.builder()
                .id(boardId)
                .user(User.builder().username("user1").build())
                .title("")
                .content("content")
                .createdDate(LocalDateTime.now())
                .deleteYn(false)
                .build();

        when(boardRepository.findById(boardId)).thenReturn(Optional.of(boardToDelete));

        //when
        int result = boardServiceimpl.deleteBoard(boardId);

        //then
        assertEquals(1,result);
        verify(boardRepository).findById(boardId);
        verify(boardRepository).updateBoardByDeleteYn(boardId);
    }

    @Test
    void deleteBoard_failure() {

        //given
        Long boardId = 2L;

        when(boardRepository.findById(boardId)).thenReturn(Optional.empty());

        //when
        int result = boardServiceimpl.deleteBoard(boardId);

        //then
        assertEquals(0,result);
        verify(boardRepository).findById(boardId);
        verify(boardRepository, never()).updateBoardByDeleteYn(any());
    }

}