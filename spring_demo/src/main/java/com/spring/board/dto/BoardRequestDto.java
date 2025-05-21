package com.spring.board.dto;


import com.spring.board.entity.Board;
import com.spring.board.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardRequestDto { //게시글 create update 요청용
    private String title;
    private String content;
    private Long userId;

    public Board toEntity(User user){
        return Board.builder()
                .title(title)
                .content(content)
                .user(user)
                .build();
    }
}
