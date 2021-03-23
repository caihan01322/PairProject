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

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request,Model model){
        String selectTerm=request.getParameter("selectTerm");
        String selectItem=request.getParameter("selectItem");
        String selectMode=request.getParameter("selectMode");
        Map<String,String> map=new HashMap<>();
        map.put(selectTerm,selectItem);
        List<Paper> papers;
        if(selectMode.equals("fuzzy")){//模糊查询
            papers = paperMapper.selectPaperByFuzzyMode(map);
        }else {//精确查询
            papers = paperMapper.selectPaperByPreciseMode(map);
        }
        model.addAttribute("papers",papers);
        return "paperList";
    }
}
