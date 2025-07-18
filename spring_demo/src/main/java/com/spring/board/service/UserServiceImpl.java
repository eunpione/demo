package com.spring.board.service;

import com.spring.board.dto.LoginRequestDto;
import com.spring.board.dto.UserDto;
import com.spring.board.dto.UserRequestDto;
import com.spring.board.entity.User;
import com.spring.board.exception.UserNotFoundException;
import com.spring.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){ //누락
        this.userRepository = userRepository;
    }


    @Override
    public UserDto createUser(UserRequestDto dto){ //유저 등록(회원가입) 하고 나서 정상등록되었다는 차원에서 return
        User user = dto.toEntity();
        User savedUser = userRepository.save(user);
        return UserDto.fromEntity(savedUser);
    }

    @Override
    public UserDto login(LoginRequestDto dto){
        User user = userRepository.findByUsernameAndPassword(dto.getUsername(), dto.getPassword())
                .orElseThrow(()-> new UserNotFoundException("아이디와 비밀번호를 확인해주세요"));
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto getUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다. " + id));
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto getUserByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException("사용자가 존재하지 않습니다. " + username));
        return UserDto.fromEntity(user);
    }

    @Override
    public UserDto updateUser(UserRequestDto dto){
        String userName = dto.getUsername();
        User user = userRepository.findByUsername(userName)
                .orElseThrow(()-> new UserNotFoundException("수정하고자 하는 사용자가 존재하지 않습니다." + userName));

//        user.setEmail(dto.getEmail()); //dto에서만 set 가능함에 따라 수정 필요
//        user.setPassword(dto.getPassword());

//        try{
//            user.update(dto.getEmail(), dto.getPassword());
//            return UserDto.fromEntity(user);
//        }catch(IllegalArgumentException e){
//            throw new RuntimeException("회원 정보 수정 실패: "+ e.getMessage());
//        }catch (Exception e){
//            throw new RuntimeException("알 수 없는 오류로 회원정보 변경 실패", e);

        return null;
    }
}
