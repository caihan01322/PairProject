package com.pair.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @RequestMapping("/paperList")
    public String getPaperList(Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Paper> papers = paperMapper.selectPaperListWithoutKeywords();
        for (int i = 0; i < papers.size(); i++) {
            papers.get(i).setKeywords(keywordMapper.getKeyWordsByPid(papers.get(i).getPid()));
        }
        model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
        return "showPaper";
    }

    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request, Model model) {
        String selectTerm = request.getParameter("selectTerm");
        String selectItem = request.getParameter("selectItem");
        String selectMode = request.getParameter("selectMode");
        Map<String, Object> map = new HashMap<>();
        map.put(selectTerm, selectItem);
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
        keywordMapper.updateKeywordByPid(pid);
        paperKeywordMapper.deletePKByPid(pid);
        paperMapper.deletePaperByPid(pid);
        return "redirect:/paperList";
    }

}
