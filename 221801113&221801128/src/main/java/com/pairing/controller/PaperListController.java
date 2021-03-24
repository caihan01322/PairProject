package com.pairing.controller;

import com.pairing.bean.Paper;
import com.pairing.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PaperListController {

    @Autowired
    PaperService paperService;

    @GetMapping("/paper_collect")
    public String paper_collect() {
        return "/paperList/paper_collect";
    }

    @GetMapping("/main")
    public String paper_main() { return "main"; }

    @GetMapping("/paper_by_pagenum")
    @ResponseBody
    public List<Paper> getPaperByPageNum(@RequestParam("pageNum") int pageNum) {
        return paperService.getPaperByPageNum(pageNum);
    }

    @GetMapping("/total_num")
    @ResponseBody
    public int getPaperTotalNum() {
        return paperService.getPaperTotalNum();
    }
}
