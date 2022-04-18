package com.example.lcapp.serviceImpl;

import com.example.lcapp.Repository.ConfirmationTokenRepository;
import com.example.lcapp.entity.ConfirmationToken;
import com.example.lcapp.service.ConfirmationTokenService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ConfirmationTokenServiceImplementation implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationTokenServiceImplementation(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public ConfirmationToken createConfirmationToken() {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setExpiredAt(LocalDateTime.now().plusMinutes(5));

        confirmationTokenRepository.save(confirmationToken);
        return confirmationToken;

    }

    @Override
    public void saveToken(ConfirmationToken token) {

        confirmationTokenRepository.save(token);

    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void removeToken(ConfirmationToken token) {
        confirmationTokenRepository.delete(token);
    }

    @Override
    public void removeTokenByToken(String token) {
        confirmationTokenRepository.removeConfirmationTokenByToken(token);
    }

}
