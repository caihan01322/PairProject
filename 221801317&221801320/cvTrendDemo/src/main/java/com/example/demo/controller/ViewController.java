package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/chart")
    public String chart(Model model) {
        return "chart";
    }
}
