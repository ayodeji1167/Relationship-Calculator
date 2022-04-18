package com.example.lcapp.controller;

import com.example.lcapp.dto.RPdto;
import com.example.lcapp.entity.Email;
import com.example.lcapp.service.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @ModelAttribute("email")
    public Email createAnEmail() {
        return new Email();
    }

    @RequestMapping("/send")
    public String sendEmail() {
        return "send-email";
    }

    @RequestMapping("/process")
    public String sent(@ModelAttribute("email") Email email, HttpServletRequest request) {

        HttpSession session = request.getSession();
        RPdto RPdto = (com.example.lcapp.dto.RPdto) session.getAttribute("lcname");
        emailService.sendResultMail(RPdto.getName(), email.getUserEmail(), RPdto.getResult());
        return "redirect:/email/send?success";
    }
}

