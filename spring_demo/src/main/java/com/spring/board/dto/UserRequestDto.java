package com.spring.board.dto;


import com.spring.board.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto { //회원가입 요청용

    private String username;
    private String password;
    private String email;
    private String role;

    public User toEntity(){
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .role(role)
                .build();
    }
}
