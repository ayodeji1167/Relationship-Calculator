package com.example.lcapp.serviceImpl;

import com.example.lcapp.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendResultMail(String username, String toEmail, String result) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText("Hi " + username + " The result predicted by the love calculator app is " + result);
        message.setTo(toEmail);
        message.setFrom("akintundegbenga30@gmail.com");
        message.setSubject("Love Calculation App");

        javaMailSender.send(message);

    }

    @Override
    public void sendConfirmationEmail(String toEmail, String urlLink) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setText("Hi " + " confirm your account by clicking the  link " + urlLink);
        message.setTo(toEmail);
        message.setFrom("akintundegbenga30@gmail.com");
        message.setSubject("Secured Otp");

        javaMailSender.send(message);


    }
}
