package com.ashis.utils;

import com.ashis.entities.User;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails {

    private User byUsername;

    public CustomUserDetail(User byUsername) {
        this.byUsername=byUsername;

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return List.of(
                new SimpleGrantedAuthority("ROLE_" + byUsername.getRole())
        );
    }

    @Override
    public @Nullable String getPassword() {
        return byUsername.getPassword();
    }

    @Override
    public String getUsername() {
        return byUsername.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
