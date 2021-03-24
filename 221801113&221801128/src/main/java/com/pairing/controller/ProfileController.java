package com.pairing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @GetMapping("/personal_info")
    public String personal_info() {
        return "/profile/personal_info";
    }
}
