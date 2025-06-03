package com.spring.board.controller;


import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserRequestDto;
import com.spring.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eun")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    //회원가입
    @PostMapping("/user")
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequestDto userRequestDto) {
        UserDto createdUser = userService.createUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    }

    //사용자 정보 조회 By Id
    @GetMapping("/user/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId){
        UserDto getUser = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(getUser);
    }

    //사용자 정보 조회 By UserName
    @GetMapping("/user/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username){
        UserDto getUserByUsername = userService.getUserByUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(getUserByUsername);
    }
    
    //사용자 정보 수정
    @PutMapping("/user")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserRequestDto userRequestDto){
        UserDto updateOrNot = userService.updateUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(updateOrNot);
    }
}
