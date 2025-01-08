package com.team.teamproject_1.entity.user.controller;



import com.team.teamproject_1.entity.user.dto.UserDto;
import com.team.teamproject_1.entity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("userDto", new UserDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginRequest(){
        return "success";
    }

    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("userDto", new UserDto());
        return "signup";
    }

    @PostMapping("/signup")
    public String signupRequest(@ModelAttribute UserDto userDto){
        userService.save(userDto);
        return "success";
    }

}
