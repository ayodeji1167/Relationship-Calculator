package com.example.lcapp.security;

import com.example.lcapp.entity.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;


public class AppUserDetails implements UserDetails {

    private final String email;
    private final String password;
    private final Set<? extends GrantedAuthority> authorities;
    private final boolean enabled;

    public AppUserDetails(AppUser user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.authorities = user.getRoles().grantedAuthorities();
        this.enabled = user.isEnabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
        return enabled;
    }
}
