package com.pair.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PaperController {
    @Autowired
    PaperMapper paperMapper;
    @RequestMapping("/paperList")
    public String getPaperList(Model model){

        List<Paper> papers = paperMapper.selectAllPapers();
        model.addAttribute("papers",papers);
        return "paperList";
    }
}
