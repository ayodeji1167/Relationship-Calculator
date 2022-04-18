package com.example.lcapp.entity;


import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    ADMIN(Sets.newHashSet("read", "write")),
    USER(Sets.newHashSet("read"));


    private Set<String> authorities;

    Role(Set<String> auth) {
        this.authorities = auth;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Set<SimpleGrantedAuthority> grantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities1 = getAuthorities().stream().
                map(SimpleGrantedAuthority::new).collect(Collectors.toSet());

        authorities1.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities1;

    }
}
