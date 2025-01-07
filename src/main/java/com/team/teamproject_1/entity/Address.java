package com.team.teamproject_1.entity;

import jakarta.persistence.*;

import java.util.List;

@Table
@Entity
public class Address {
    @Id
    @GeneratedValue

    private String city;

    private String street;

    private String zipcode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
}
