package com.pair.controller;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperKeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Keyword;
import com.pair.pojo.Paper;
import com.pair.service.KeywordService;
import com.pair.service.PaperKeywordService;
import com.pair.service.PaperService;
import com.sun.java.browser.plugin2.liveconnect.v1.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class PaperController {
    @Autowired
    KeywordService keywordService;
    @Autowired
    PaperService paperService;
    @Autowired
    PaperKeywordService paperKeywordService;
    private List<String> paperIds=new ArrayList<>();
    private int begin;
    private int end;
    private int num=9;
    private int pages;
    private int currentPage;


    @RequestMapping("/paperList")
    public String getPaperList(Model model, @RequestParam(defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        List<Paper> papers = paperService.selectPaperListWithoutKeywords();
        for (int i = 0; i < papers.size(); i++) {
            papers.get(i).setKeywords(keywordService.getKeyWordsByPid(papers.get(i).getPid()));
        }
        model.addAttribute("pageInfo",new PageInfo<Paper>(papers));
        return "showPaper";
    }

    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request,Model model) {
        String selectTerm,selectItem,selectMode;
        if(request.getAttribute("selectItem")==null){
            selectTerm = request.getParameter("selectTerm");
            selectItem = request.getParameter("selectItem");
            selectMode = request.getParameter("selectMode");
        }else{
            selectTerm = (String) request.getAttribute("selectTerm");
            selectItem = (String) request.getAttribute("selectItem");
            selectMode = (String) request.getAttribute("selectMode");
        }
        //查询的内容
        Map<String, Object> map = new HashMap<>();
        map.put(selectTerm, selectItem);
        if (selectMode.equals("fuzzy")) {//模糊查询
            paperIds = paperService.getPaperIdByFuzzyMode(map);
        } else {//精确查询
            paperIds = paperService.getPaperIdByPreciseMode(map);
        }
        begin=0;
        if(paperIds.size()-1<8){
            end=paperIds.size()-1;
        }else{
            end=8;
        }
        List<Paper> papers=new ArrayList<>();
        for(int i=begin;i<=end;i++){
            papers.add(paperService.getPaperById(paperIds.get(i)));
        }
        model.addAttribute("papers",papers);
        model.addAttribute("pages",paperIds.size()/9+1);
        model.addAttribute("currentPages",begin/9+1);
        return "paperList";
    }
    @RequestMapping("/selectAgain")
    public String selectAgain(Model model){
        List<Paper> papers=new ArrayList<>();
        for(int i=begin;i<=end;i++){
            papers.add(paperService.getPaperById(paperIds.get(i)));
        }
        model.addAttribute("papers",papers);
        model.addAttribute("pages",paperIds.size()/9+1);
        model.addAttribute("currentPages",begin/9+1);
        return "paperList";
    }

    @RequestMapping("/deletePaper/{pid}")
    public String paperSelect(@PathVariable("pid") String pid) {
        keywordService.updateKeywordByPid(pid);
        paperKeywordService.deletePKByPid(pid);
        paperService.deletePaperByPid(pid);
        return "redirect:/paperList";
    }
    @RequestMapping("/previousPage")
    public String previousPage(){
        begin-=9;
        if(begin<0){
            begin=0;
        }else{
            end-=9;
        }
        return "redirect:/selectAgain";
    }
    @RequestMapping("/nextPage")
    public String nextPage(){
        end+=9;
        if(end>paperIds.size()-1){
            end=paperIds.size()-1;
        }else{
            begin+=9;
        }
        return "redirect:/selectAgain";
    }
    @RequestMapping("/beginPage")
    public String beginPage(){
        begin=0;
        if(paperIds.size()-1>8){
            end=8;
        }else{
            end=paperIds.size()-1;
        }
        return "redirect:/selectAgain";
    }
    @RequestMapping("/endPage")
    public String endPage(){
        end=paperIds.size()-1;
        begin=paperIds.size()-1-(paperIds.size()-1)%9;
        return "redirect:/selectAgain";
    }
}
