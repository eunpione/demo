package com.spring.board.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.spring.board.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto { //response용

    private Long id;
    private String username;
    private String email;
    private String role;
    private String createdDate;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .createdDate(user.getCreatedDate().toString())
                .build();
    }
}
