package com.team.teamproject_1.repository;

import com.team.teamproject_1.entity.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Users findByEmail(String email) {
        String jpql = "SELECT u FROM Users u WHERE u.email = :email";

        return entityManager.createQuery(jpql, Users.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
