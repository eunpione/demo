package com.spring.board.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.spring.board.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDto { //response용
    private Long id;
    private String title;
    private String content;
    private String authorUsername;
    private String createdDate;
    private String changedDate;


    public static BoardDto fromEntity(Board board){
        return BoardDto.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .authorUsername(board.getUser().getUsername())
                .createdDate(board.getCreatedDate().toString())
                .build();
    }

}
