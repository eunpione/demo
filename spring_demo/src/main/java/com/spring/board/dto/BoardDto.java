package com.spring.board.dto;


import com.spring.board.entity.Board;
import lombok.*;

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
                .changedDate(board.getChangedDate().toString())
                .build();
    }

}
