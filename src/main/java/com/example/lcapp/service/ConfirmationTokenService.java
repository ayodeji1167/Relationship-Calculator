package com.example.lcapp.service;

import com.example.lcapp.entity.ConfirmationToken;

public interface ConfirmationTokenService {

    ConfirmationToken createConfirmationToken();

    void saveToken(ConfirmationToken token);

    ConfirmationToken findByToken(String token);

    void removeToken(ConfirmationToken token);

    void removeTokenByToken(String token);
}
