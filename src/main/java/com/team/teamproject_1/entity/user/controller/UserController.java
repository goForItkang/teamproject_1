package com.team.teamproject_1.entity.user.controller;

import com.team.teamproject_1.entity.user.dto.UserDto;
import com.team.teamproject_1.entity.user.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;

@Slf4j
@Controller
public class UserController {
    private final UserService userService;
    private final HttpHeaders header;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        this.header = new HttpHeaders();
    }

    @GetMapping("/login/success")
    public ResponseEntity<String> loginSuccess(HttpServletRequest request) {
        String jwtToken = findJwtToken(request);
        header.set("Authorization","Bearer "+ jwtToken);
        return ResponseEntity.ok().headers(header).body(jwtToken);
    }

    private String findJwtToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            return Arrays.stream(cookies)
                    .filter(cookie -> "JwtToken".equals(cookie.getName()))
                    .map(Cookie::getValue)
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signupRequest(@RequestBody UserDto userDto){
        log.info("userDto : {}", userDto);
        userService.save(userDto);
        return ResponseEntity.ok("ok");
    }

}
