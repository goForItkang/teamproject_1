package com.team.teamproject_1.repository;


import com.team.teamproject_1.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Long> {


    Users findByEmail(String email);
}
