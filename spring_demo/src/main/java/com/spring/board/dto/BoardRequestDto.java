package com.spring.board.dto;


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
}
