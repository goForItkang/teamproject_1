package com.team.teamproject_1.security.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import sports_shop.project1.domain.user.dto.UserDto;
import sports_shop.project1.domain.user.entity.User;
import sports_shop.project1.domain.user.repository.UserRepository;
import sports_shop.project1.security.model.Principal;
import sports_shop.project1.security.model.UserDetailsDto;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        User user = userRepository.findByLoginId(loginId);
        if(user == null){
            log.error("user is null");
            throw new UsernameNotFoundException("User not found with loginId: " + loginId);
        }
        UserDetailsDto userDetailsDto = new UserDetailsDto(userToUserDto(user));
        return new Principal(userDetailsDto);
    }


    private UserDto userToUserDto(User user) {
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
}
