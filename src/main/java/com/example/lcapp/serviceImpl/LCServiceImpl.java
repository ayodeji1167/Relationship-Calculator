package com.example.lcapp.serviceImpl;

import com.example.lcapp.service.LCService;
import org.springframework.stereotype.Service;

@Service
public class LCServiceImpl implements LCService {
    @Override
    public String result(String name, String crushName) {
        int wordLength = name.length() + crushName.length();
        int index = wordLength % 5;

        if (index == 0)
            return "FRIEND";
        else if (index == 1)
            return "LOVE";
        else if (index == 2)
            return "AFFECTION";
        else if (index == 3)
            return "MARRIAGE";
        else if (index == 4)
            return "ENEMY";
        else
            return "UNRESOLVED";
    }


}