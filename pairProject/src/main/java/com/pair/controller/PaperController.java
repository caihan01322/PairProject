package com.pair.controller;


import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperKeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaperController {
    @Autowired
    PaperMapper paperMapper;
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    PaperKeywordMapper paperKeywordMapper;
    private int startIndex=0;
    private int indexNum=166;

    @RequestMapping("/paperList")
    public String getPaperList(Model model, HttpServletResponse response) {
        List<Paper> papers = paperMapper.selectAllPapers(startIndex,indexNum);
        model.addAttribute("papers", papers);
        return "paperList";
    }

    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request, Model model) {
        String selectTerm = request.getParameter("selectTerm");
        String selectItem = request.getParameter("selectItem");
        String selectMode = request.getParameter("selectMode");
        Map<String, Object> map = new HashMap<>();
        map.put(selectTerm, selectItem);
        map.put("startIndex",startIndex);
        map.put("indexNum",indexNum);
        List<Paper> papers;
        if (selectMode.equals("fuzzy")) {//模糊查询
            papers = paperMapper.selectPaperByFuzzyMode(map);
        } else {//精确查询
            papers = paperMapper.selectPaperByPreciseMode(map);
        }
        model.addAttribute("papers", papers);
        return "/paperList";
    }

    @RequestMapping("/deletePaper/{pid}")
    public String paperSelect(@PathVariable("pid") String pid) {
        //keywordMapper.deleteKeywordByPid(pid);
        //paperKeywordMapper.deletePKByPid(pid);
        paperMapper.deletePaperByPid(pid);
        return "redirect:/paperList";
    }
    @RequestMapping("/nextPage")
    public String nextPage(Model model){
        startIndex+=166;
        List<Paper> papers = paperMapper.selectAllPapers(startIndex,indexNum);
        model.addAttribute("papers", papers);
        return "paperList";
    }
    @RequestMapping("/perviousPage")
    public String previousPage(Model model){
        startIndex-=166;
        if(startIndex<=0){
            startIndex=0;
        }
        List<Paper> papers = paperMapper.selectAllPapers(startIndex,indexNum);
        model.addAttribute("papers", papers);
        return "paperList";
    }
}
