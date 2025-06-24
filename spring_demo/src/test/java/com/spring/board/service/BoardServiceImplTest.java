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
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

//    @Test
//    void getBoard() {
//    }
//
//    @Test
//    void updateBoard() {
//    }
//
//    @Test
//    void deleteBoard() {
//    }

}