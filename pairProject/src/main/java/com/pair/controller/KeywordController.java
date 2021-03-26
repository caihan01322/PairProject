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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Keyword> keyWords = keywordMapper.getKeyWords();
        String kws[] = new String[10];
        int nums[]=new int[10];
        for (int i = 0; i < keyWords.size(); i++) {
            kws[i] = keyWords.get(i).getKeyword();
            nums[i]=keyWords.get(i).getNum();
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
        model.addAttribute("num0",nums[0]);
        model.addAttribute("num1",nums[1]);
        model.addAttribute("num2",nums[2]);
        model.addAttribute("num3",nums[3]);
        model.addAttribute("num4",nums[4]);
        model.addAttribute("num5",nums[5]);
        model.addAttribute("num6",nums[6]);
        model.addAttribute("num7",nums[7]);
        model.addAttribute("num8",nums[8]);
        model.addAttribute("num9",nums[9]);

        return "cloud";
    }

    @RequestMapping("/getPapers/{keyword}")
    public String getPapers(@PathVariable("keyword") String keyword,Model model) {

        List<Paper> papers=paperMapper.getPapersByKeyword(keyword);
        model.addAttribute("papers", papers);
        return "paperList";
    }
    @RequestMapping("/top10words/{meeting}")
    @ResponseBody
    public List<Keyword> getTop10ByMeeting(@PathVariable("meeting") String meeting){
        String publisher=meeting;
        if(publisher==null){
            publisher="CVPR";
        }
        List<Keyword> top10Keyword = keywordMapper.getTop10Keyword(publisher);
        return top10Keyword;
    }

    @RequestMapping("/top10")
    public String getTop10(HttpServletRequest request,Model model){
        String publisher=request.getParameter("meeting");
        if(publisher==null){
            publisher="CVPR";
        }
        String keyword=request.getParameter("words");
        if(keyword==null){
            keyword="Cameras";
        }
        //某顶会几年间的top10词汇
        List<Keyword> top10Keyword = keywordMapper.getTop10Keyword(publisher);

        String top10Keywords[]=new String[10];
        int recentTenYear[]=new int[10];
        String searchKid="kid_1616334190243";
        for (int i = 0; i < top10Keyword.size(); i++) {
            top10Keywords[i]=top10Keyword.get(i).getKeyword();
            if(top10Keyword.get(i).getKeyword().equals(keyword))
                searchKid=top10Keyword.get(i).getKid();
        }
        for (int i = 0; i < 10; i++) {
            Map<String,String> map=new HashMap<>();
            map.put("kid",searchKid);
            map.put("publicationYear",String.valueOf(2011+i));
            recentTenYear[i]=paperMapper.getPaperNum(map);
        }


        model.addAttribute("title",publisher);
        model.addAttribute("keyword",keyword);
        model.addAttribute("top0",top10Keywords[0]);
        model.addAttribute("top1",top10Keywords[1]);
        model.addAttribute("top2",top10Keywords[2]);
        model.addAttribute("top3",top10Keywords[3]);
        model.addAttribute("top4",top10Keywords[4]);
        model.addAttribute("top5",top10Keywords[5]);
        model.addAttribute("top6",top10Keywords[6]);
        model.addAttribute("top7",top10Keywords[7]);
        model.addAttribute("top8",top10Keywords[8]);
        model.addAttribute("top9",top10Keywords[9]);

        model.addAttribute("year0",recentTenYear[0]);
        model.addAttribute("year1",recentTenYear[1]);
        model.addAttribute("year2",recentTenYear[2]);
        model.addAttribute("year3",recentTenYear[3]);
        model.addAttribute("year4",recentTenYear[4]);
        model.addAttribute("year5",recentTenYear[5]);
        model.addAttribute("year6",recentTenYear[6]);
        model.addAttribute("year7",recentTenYear[7]);
        model.addAttribute("year8",recentTenYear[8]);
        model.addAttribute("year9",recentTenYear[9]);
        return "lineChart";
    }

}
