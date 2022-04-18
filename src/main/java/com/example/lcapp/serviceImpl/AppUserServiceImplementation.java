package com.example.lcapp.serviceImpl;

import com.example.lcapp.Repository.ConfirmationTokenRepository;
import com.example.lcapp.Repository.UserRepository;
import com.example.lcapp.dto.AppUserDto;
import com.example.lcapp.entity.AppUser;
import com.example.lcapp.entity.ConfirmationToken;
import com.example.lcapp.entity.Role;
import com.example.lcapp.security.AppUserDetails;
import com.example.lcapp.service.AppUserService;
import com.example.lcapp.service.ConfirmationTokenService;
import com.example.lcapp.service.EmailService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.Objects;


@Service
public class AppUserServiceImplementation implements AppUserService {
    private final static String BASE_URL = "http://localhost:8080";
    private final ConfirmationTokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;
    private final ConfirmationTokenRepository tokenRepository;

    public AppUserServiceImplementation(UserRepository userRepo, PasswordEncoder passwordEncoder, ConfirmationTokenService tokenService, EmailService emailService, ConfirmationTokenRepository tokenRepository) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.emailService = emailService;
        this.tokenRepository = tokenRepository;
    }


    public void saveUser(AppUserDto appUserDto) {
        AppUser appUser = saveMethod(appUserDto);
        appUser.setRoles(Role.USER);

        userRepo.save(appUser);

        sendConfirmationEmail(appUser);
    }

    @Override
    public void saveAdmin(AppUserDto appUserDto) {
        AppUser appUser = saveMethod(appUserDto);
        appUser.setRoles(Role.ADMIN);
        userRepo.save(appUser);

        sendConfirmationEmail(appUser);
    }

    private AppUser saveMethod(AppUserDto appUserDto) {
        AppUser appUser = new AppUser();

        appUser.setFirstName(appUserDto.getFirstName());
        appUser.setLastName(appUserDto.getLastName());
        appUser.setEmail(appUserDto.getEmail());
        appUser.setPassword(passwordEncoder.encode(appUserDto.getPassword()));

        appUser.setEnabled(false);


        return appUser;
    }

    //------------------------------------


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = userRepo.findAppUserByEmail(email).orElseThrow(
                () -> new RuntimeException("User with email " + email + " not found"));

        return new AppUserDetails(user);

    }

    public void sendConfirmationEmail(AppUser appUser) {
        ConfirmationToken confirmationToken = tokenService.createConfirmationToken();
        confirmationToken.setUser(appUser);
        tokenRepository.save(confirmationToken);

        //--------------- CREATE THE CONFIRMATION LINK ------

        final String url = UriComponentsBuilder.fromHttpUrl(BASE_URL).
                path("/registration/confirm").queryParam("token", confirmationToken.getToken()).toUriString();

        emailService.sendConfirmationEmail(appUser.getEmail(), url);

    }

    public boolean userVerify(String token) {

        ConfirmationToken confirmationToken = tokenRepository.findByToken(token);


        if (Objects.isNull(confirmationToken) || !confirmationToken.getToken().equals(token)) {

            throw new RuntimeException("Token " + token + " is invalid");

        }

        LocalDateTime expiredAt = confirmationToken.getExpiredAt();

        if (LocalDateTime.now().isAfter(expiredAt)) {
            throw new RuntimeException("Token " + token + " HAS EXPIRED");

        }

        AppUser user = confirmationToken.getUser();
        if (Objects.isNull(user)) {
            return false;
        }

        user.setEnabled(true);
        userRepo.save(user);
        tokenService.removeToken(confirmationToken);

        return true;
    }


}
