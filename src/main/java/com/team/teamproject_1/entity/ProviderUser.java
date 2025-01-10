package com.team.teamproject_1.security.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface ProviderUser {

    Collection<? extends GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();

}
