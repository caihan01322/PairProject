package cn.zcx.pa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController
{
    @GetMapping("/searchEssay")
    public String searchEssay(Model model)
    {
        model.addAttribute("user","zcx");
        return "searchEssay";
    }

}
