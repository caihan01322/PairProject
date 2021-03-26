package cn.zcx.pa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController
{
    @GetMapping("/index")
    public String searchEssay(Model model)
    {
        model.addAttribute("user","zcx");
        return "index";
    }

    @GetMapping("/advancedSearch")
    public String advancedSearch(Model model)
    {
        return "advancedSearch";
    }

    @GetMapping("/essayDetail")
    public String essayDetail(Model model)
    {
        return "essayDetail";
    }

}
