package com.team.teamproject_1.service;

import com.team.teamproject_1.dto.UserDto;
import com.team.teamproject_1.entity.Users;
import com.team.teamproject_1.repository.UserRepository;
import com.team.teamproject_1.entity.Principal;
import com.team.teamproject_1.entity.UserDetailsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email : " + email);
        log.info("email : {}", email);
        Users users = userRepository.findByEmail(email);
        if(users == null){
            log.error("user is null");
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        //******** 수정 변경 사항 1.10 *************
        // getUsername -> getEmail로 수정바람
        UserDetailsDto userDetailsDto = new UserDetailsDto(userToUserDto(users));
        return new Principal(userDetailsDto);
    }


    private UserDto userToUserDto(Users users){

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
