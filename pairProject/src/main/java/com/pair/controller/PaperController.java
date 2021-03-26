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
        PageHelper.startPage(pageNum,10);
        List<Paper> papers = paperMapper.selectPaperListWithoutKeywords();
        for (int i = 0; i < papers.size(); i++) {
            papers.get(i).setKeywords(keywordMapper.getKeyWordsByPid(papers.get(i).getPid()));
        }
        model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
        return "showPaper";
    }

    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request, Model model,@RequestParam(defaultValue = "1") Integer pageNum) {
        //
        String selectTerm = request.getParameter("selectTerm");
        //输入信息
        String selectItem = request.getParameter("selectItem");
        //查询模式
        String selectMode = request.getParameter("selectMode");

        //查询的内容
        Map<String, String> map = new HashMap<>();
        map.put(selectTerm, selectItem);

        List<Paper> papers=null;

        if (selectMode.equals("fuzzy")) {//模糊查询
            if(selectItem.equals("keyword")){
                PageHelper.startPage(pageNum,10);
                List<String> kids = keywordMapper.getKidByFuzzyMode(map);
                for (int i = 0; i < kids.size(); i++) {
                    List<String> pids = paperKeywordMapper.getPid(kids.get(i));
                    for (int j = 0; j < pids.size(); j++) {
                        papers.add(paperMapper.getPapersByPid(pids.get(i)));
                    }
                }
                model.addAttribute("kidsInfo",new PageInfo<String>(kids));
                model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
                return "paperList2";
            }
            else {
                PageHelper.startPage(pageNum,10);
                papers = paperMapper.getPidByFuzzyModeByPaperInfo(map);
                for (int i = 0; i < papers.size(); i++) {
                    papers.get(i).setKeywords(keywordMapper.getKeyWordsByPid(papers.get(i).getPid()));
                }
                model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
                return "showPaper";
            }
        } else {//精确查询
            if(selectItem.equals("keyword")){
                PageHelper.startPage(pageNum,10);
                List<String> kids = keywordMapper.getKidByByPreciseMode(map);
                for (int i = 0; i < kids.size(); i++) {
                    List<String> pids = paperKeywordMapper.getPid(kids.get(i));
                    for (int j = 0; j < pids.size(); j++) {
                        papers.add(paperMapper.getPapersByPid(pids.get(i)));
                    }
                }
                model.addAttribute("kidsInfo",new PageInfo<String>(kids));
                model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
                return "paperList2";
            }
            else {
                PageHelper.startPage(pageNum,10);
                papers = paperMapper.selectPaperByPreciseModeByPaperInfo(map);
                for (int i = 0; i < papers.size(); i++) {
                    papers.get(i).setKeywords(keywordMapper.getKeyWordsByPid(papers.get(i).getPid()));
                }
                model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
                return "showPaper";
            }
        }
    }

    @RequestMapping("/deletePaper/{pid}")
    public String paperSelect(@PathVariable("pid") String pid) {
        keywordMapper.updateKeywordByPid(pid);
        paperKeywordMapper.deletePKByPid(pid);
        paperMapper.deletePaperByPid(pid);
        return "redirect:/paperList";
    }

}
