package com.spring.board.dto;


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

}
