package com.pairing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaperListController {
    @GetMapping("/paper_collect")
    public String paper_collect() {
        return "/paperList/paper_collect";
    }

    @GetMapping("/main")
    public String paper_main() { return "main"; }
}
