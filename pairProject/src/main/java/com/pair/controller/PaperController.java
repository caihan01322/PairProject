package com.pair.controller;

import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/paperSelect")
    public String paperSelect(HttpServletRequest request,Model model){
        String selectTerm=request.getParameter("selectTerm");
        String selectItem=request.getParameter("selectItem");
        if(selectTerm.equals("title")){
            List<Paper> papers = paperMapper.selectPaperByTitle(selectItem);
            model.addAttribute("papers",papers);
        }else if(selectTerm.equals("abs")){
            List<Paper> papers = paperMapper.selectPaperByAbs(selectItem);
            model.addAttribute("papers",papers);
        }else if(selectTerm.equals("publisher")){
            List<Paper> papers = paperMapper.selectPaperByPublisher(selectItem);
            model.addAttribute("papers",papers);
        }else if(selectTerm.equals("publicationYear")){
            List<Paper> papers = paperMapper.selectPaperByPublicationYear(selectItem);
            model.addAttribute("papers",papers);
        }else{
            List<Paper> papers = paperMapper.selectPaperByKeyword(selectItem);
            model.addAttribute("papers",papers);
        }
        return "paperList";
    }
}
