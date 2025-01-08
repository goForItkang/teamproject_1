package com.team.teamproject_1.entity.user.repository;

import com.team.teamproject_1.entity.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {


    User findByLoginId(String loginId);

    @Query("SELECT u FROM User u WHERE u.loginId = :loginId AND u.password = :password")
    User findByLoginIdAndPassword(@Param("loginId") String loginId, @Param("password") String password);

    User findByUsername(String username);
}
