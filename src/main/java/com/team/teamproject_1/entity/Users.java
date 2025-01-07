package com.team.teamproject_1.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Table(name = "users")
@Entity
public class Users {
    @Id
    @GeneratedValue
    private Long id;

    private String email;

    private String password;

    private String username;

    private String phoneNumber;

    private LocalDateTime birthday;

    private LocalDateTime enrollerDate;

    private LocalDateTime deleteDate;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "users")
    private List<Order> orders;

    @OneToMany(mappedBy = "users")
    private List<Comment> comments;

    @OneToMany(mappedBy = "users")
    private List<Address> addresses;

    @OneToMany(mappedBy = "users")
    private List<Cart> carts;
}
