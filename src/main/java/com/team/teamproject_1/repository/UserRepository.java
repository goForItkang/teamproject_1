package com.team.teamproject_1.entity.user.repository;

import com.team.teamproject_1.entity.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<Users, Long> {


    Users findByEmail(String email);
}
