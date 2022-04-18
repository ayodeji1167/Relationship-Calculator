package com.example.lcapp.controller;

import com.example.lcapp.dto.AppUserDto;
import com.example.lcapp.service.AppUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final AppUserService service;


    public RegistrationController(AppUserService service) {
        this.service = service;
    }

    @ModelAttribute("userInfo")
    public AppUserDto appUserDto() {
        return new AppUserDto();
    }


    @RequestMapping
    public String registerUser() {
        return "registration";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("userInfo") @Valid AppUserDto userDto) {
        service.saveUser(userDto);
        return "redirect:/registration?success";
    }

    @RequestMapping(value = "/admin/form")
    public String registerAdmin() {
        return "admin-form";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String saveAdmin(@ModelAttribute("userInfo") @Valid AppUserDto userDto) {
        service.saveAdmin(userDto);
        return "redirect:/registration?success";
    }

    //Confirm user

    @GetMapping("/confirm")
    public String confirmUser(@RequestParam(value = "token") String token, Model model) {

        if (token.isEmpty()) {

            return "redirect:/login?error";
        }
        try {
            if (!service.userVerify(token)) {

                throw new RuntimeException("invalid token");
            }

        } catch (Exception e) {
            return "redirect:/login?error";
        }

        return "redirect:/login";

    }
}

