package com.team.teamproject_1.service;


import com.team.teamproject_1.entity.Users;
import com.team.teamproject_1.repository.AuthRepository;
import com.team.teamproject_1.dto.UserDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthRepository authRepository;

    @Autowired
    public AuthService(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public UserDto findByUser(String email){
        Users user = authRepository.findByEmail(email);
        if(user != null){
            return usersToUserDto(user);
        }

        return null;
    }

    private UserDto usersToUserDto(Users users){
        return UserDto.builder()
                .id(users.getId())
                .email(users.getEmail())
                .password(users.getPassword())
                .username(users.getUsername())
                .phoneNumber(users.getPhoneNumber())
                .role(users.getRole())
                .birthday(users.getBirthday())
                .build();
    }

}
