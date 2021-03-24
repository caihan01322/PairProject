package com.pairing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaperAnalyzeController {
    @GetMapping("/hot_areas")
    public String hot_areas() {
        return "/paperAnalyze/hot_areas";
    }

    @GetMapping("/trend_compare")
    public String trend_compare() {
        return "/paperAnalyze/trend_compare";
    }
}
