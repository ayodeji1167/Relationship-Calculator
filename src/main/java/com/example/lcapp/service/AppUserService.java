package com.example.lcapp.service;

import com.example.lcapp.dto.AppUserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AppUserService extends UserDetailsService {
    void saveUser(AppUserDto appUserDto);

    void saveAdmin(AppUserDto appUserDto);

    boolean userVerify(String token);
}
