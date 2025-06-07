package com.spring.board.service;

import com.spring.board.dto.LoginRequestDto;
import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserRequestDto;

public interface UserService {

    UserDto createUser(UserRequestDto dto);
    UserDto getUser(Long id);
    UserDto getUserByUsername(String username);
    UserDto updateUser(UserRequestDto dto);
    UserDto login(LoginRequestDto dto);
}
