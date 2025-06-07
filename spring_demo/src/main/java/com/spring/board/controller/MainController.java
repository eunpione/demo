package com.spring.board.controller;

import com.spring.board.dto.LoginRequestDto;
import com.spring.board.dto.UserDto;
import com.spring.board.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    private final UserService userService;

    public MainController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public String main() {
        System.out.println(">> MainController 호출됨");
        return "Hello, Spring!";
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto dto){
        UserDto loginUser = userService.login(dto);
        return ResponseEntity.status(HttpStatus.OK).body(loginUser);
    }
}
