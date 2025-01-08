package com.team.teamproject_1.entity.user.service;

import com.team.teamproject_1.entity.user.dto.UserDto;
import com.team.teamproject_1.entity.user.entity.Role;
import com.team.teamproject_1.entity.user.entity.User;
import com.team.teamproject_1.entity.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(UserDto userDto){
        if(this.findByUser(userDto.getLoginId()) != null){
            log.info("User already exists");
            return false;
        }

        userDto.setRole(Role.USER);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userDtoToUser(userDto);

        userRepository.save(user);
        log.info("Save User : {}", user);
        return true;
    }

    public UserDto findByUser(String loginId){
        User user = userRepository.findByLoginId(loginId);
        if(user == null){
            log.info("user is null");
            return null;
        }

        return userToUserDto(user);
    }

    private UserDto userToUserDto(User user){
        return new UserDto(
                user.getId(),
                user.getLoginId(),
                user.getPassword(),
                user.getEmail(),
                user.getUsername(),
                user.getPhoneNumber(),
                user.getRole()
        );
    }

    private User userDtoToUser(UserDto userDto){
        return new User(
                userDto.getLoginId(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getUsername(),
                userDto.getPhoneNumber(),
                userDto.getRole()
        );
    }


//    public UserDto findByLoginId(String loginId){
//        User user = userRepository.findByLoginId(loginId);
//        if(user == null){
//            return null;
//        }
//
//        return modelMapper.map(user, UserDto.class);
//    }
//
//    public UserDto findByPassword(String password){
//        User user = userRepository.findByPassword(password);
//        if(user == null){
//            return null;
//        }
//
//        return modelMapper.map(user, UserDto.class);
//    }


}
