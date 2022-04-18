package com.example.lcapp.service;

public interface EmailService {
    void sendResultMail(String username, String toEmail, String result);

    void sendConfirmationEmail(String toEmail, String token);
}
