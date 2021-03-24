package com.pair.controller;


import com.pair.dao.KeywordMapper;
import com.pair.dao.PaperKeywordMapper;
import com.pair.dao.PaperMapper;
import com.pair.pojo.Keyword;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class KeywordController {

    @Autowired
    PaperMapper paperMapper;
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    PaperKeywordMapper paperKeywordMapper;

    @RequestMapping("/clouds")
    public String getKeyWords(Model model) {
        List<String> keyWords = keywordMapper.getKeyWords();
        String kws[] = new String[10];
        for (int i = 0; i < keyWords.size(); i++) {
            kws[i] = keyWords.get(i);
        }
        model.addAttribute("kw0", kws[0]);
        model.addAttribute("kw1", kws[1]);
        model.addAttribute("kw2", kws[2]);
        model.addAttribute("kw3", kws[3]);
        model.addAttribute("kw4", kws[4]);
        model.addAttribute("kw5", kws[5]);
        model.addAttribute("kw6", kws[6]);
        model.addAttribute("kw7", kws[7]);
        model.addAttribute("kw8", kws[8]);
        model.addAttribute("kw9", kws[9]);

        return "cloud";
    }

    @RequestMapping("/getPapers/{keyword}")
    public String getPapers(@PathVariable("keyword") String keyword,Model model) {
        //根据关键词查找kid
        List<String> kids = keywordMapper.getKid(keyword);
        //根据kid查找对应pid
        List<String> pids=new ArrayList<String>();
        for (int i = 0; i < kids.size(); i++) {
            List<String> pid = paperKeywordMapper.getPid(kids.get(i));
            pids.addAll(pid);
        }
        //根据pid查找对应Paper
        List<Paper> papers=new ArrayList<Paper>();

        for (int i = 0; i < pids.size(); i++) {
            papers.add(paperMapper.getPapersByPid(pids.get(i)));
        }
        model.addAttribute("papers", papers);
        return "paperList";
    }

    @RequestMapping("/top10")
    public String getTop10(HttpServletRequest request,Model model){
        String publisher=request.getParameter("meeting");
        if(publisher==null){
            publisher="CVPR";
        }
        List<Keyword> top10Keyword = keywordMapper.getTop10Keyword(publisher);

        String top10Keywords[]=new String[10];
        int wordNums[]=new int[10];
        for (int i = 0; i < top10Keyword.size(); i++) {
            top10Keywords[i]=top10Keyword.get(i).getKeyword();
            wordNums[i]=top10Keyword.get(i).getNum();
        }
        System.out.println(top10Keyword);
        model.addAttribute("title",publisher);
        model.addAttribute("top0",top10Keywords[0]);
        model.addAttribute("num0",wordNums[0]);
        model.addAttribute("top1",top10Keywords[1]);
        model.addAttribute("num1",wordNums[1]);
        model.addAttribute("top2",top10Keywords[2]);
        model.addAttribute("num2",wordNums[2]);
        model.addAttribute("top3",top10Keywords[3]);
        model.addAttribute("num3",wordNums[3]);
        model.addAttribute("top4",top10Keywords[4]);
        model.addAttribute("num4",wordNums[4]);
        model.addAttribute("top5",top10Keywords[5]);
        model.addAttribute("num5",wordNums[5]);
        model.addAttribute("top6",top10Keywords[6]);
        model.addAttribute("num6",wordNums[6]);
        model.addAttribute("top7",top10Keywords[7]);
        model.addAttribute("num7",wordNums[7]);
        model.addAttribute("top8",top10Keywords[8]);
        model.addAttribute("num8",wordNums[8]);
        model.addAttribute("top9",top10Keywords[9]);
        model.addAttribute("num9",wordNums[9]);
        return "lineChart";
    }

}
