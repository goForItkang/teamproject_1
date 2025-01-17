package com.team.teamproject_1.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    private int like;

    private LocalDateTime created_date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
