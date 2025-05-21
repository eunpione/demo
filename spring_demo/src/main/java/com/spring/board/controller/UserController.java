package com.spring.board.controller;


import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserRequestDto;
import com.spring.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //회원가입
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    //사용자 정보 조회
    @GetMapping
    public ResponseEntity<UserDto> getUser(@RequestParam Long userId){
        UserDto getUser = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(getUser);
    }
    
    //사용자 정보 수정
}
