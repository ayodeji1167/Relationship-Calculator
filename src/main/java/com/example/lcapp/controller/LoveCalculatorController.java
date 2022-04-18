package com.example.lcapp.controller;

import com.example.lcapp.dto.RPdto;
import com.example.lcapp.serviceImpl.LCServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/LC")
public class LoveCalculatorController {

    private final LCServiceImpl lcService;

    public LoveCalculatorController(LCServiceImpl lcService) {
        this.lcService = lcService;
    }

    @ModelAttribute("lcapp")
    public RPdto createLCDto() {
        return new RPdto();
    }


    @RequestMapping("/input/names")
    public String getCrushData() {
        return "crush";
    }


    @RequestMapping("/calculate")
    public String calculateResult(RPdto RPdto,
                                  Model model,
                                  HttpServletRequest request) {
        String result = lcService.result(RPdto.getName(), RPdto.getCrushName());


        model.addAttribute("lcapp", RPdto);
        RPdto.setResult(result);

        HttpSession session = request.getSession();

        session.setAttribute("lcname", RPdto);
        return "calculate";
    }


}
