package com.team.teamproject_1.entity.user.dto;

import com.team.teamproject_1.entity.user.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String loginId;
    private String password;
    private String email;
    private String username;

    //010-1234-5678
    private String phoneNumber;
    private Role role;

}
