package com.spring.board.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String main() {
        System.out.println(">> MainController 호출됨");
        return "Hello, Spring!";
    }
}
