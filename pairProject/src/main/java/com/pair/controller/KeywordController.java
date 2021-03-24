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
        //某顶会几年间的top10词汇
        List<Keyword> top10Keyword = keywordMapper.getTop10Keyword(publisher);

        String top10Keywords[]=new String[10];
        int wordNums2019[]=new int[10];
        int wordNums2016[]=new int[10];
        int wordNums2013[]=new int[10];

        for (int i = 0; i < top10Keyword.size(); i++) {
            top10Keywords[i]=top10Keyword.get(i).getKeyword();

            Map<String,String> map2019=new HashMap<>();
            map2019.put("kid",top10Keyword.get(i).getKid());
            map2019.put("publicationYear","2018");
            wordNums2019[i]+=paperMapper.getPaperNum(map2019);
            map2019.put("publicationYear","2019");
            wordNums2019[i]+=paperMapper.getPaperNum(map2019);
            map2019.put("publicationYear","2020");
            wordNums2019[i]+=paperMapper.getPaperNum(map2019);

            Map<String,String> map2016=new HashMap<>();
            map2016.put("kid",top10Keyword.get(i).getKid());
            map2016.put("publicationYear","2015");
            wordNums2016[i]+=paperMapper.getPaperNum(map2016);
            map2016.put("publicationYear","2016");
            wordNums2016[i]+=paperMapper.getPaperNum(map2016);
            map2016.put("publicationYear","2017");
            wordNums2016[i]+=paperMapper.getPaperNum(map2016);

            Map<String,String> map2013=new HashMap<>();
            map2013.put("kid",top10Keyword.get(i).getKid());
            map2013.put("publicationYear","2012");
            wordNums2013[i]+=paperMapper.getPaperNum(map2013);
            map2013.put("publicationYear","2013");
            wordNums2013[i]+=paperMapper.getPaperNum(map2013);
            map2013.put("publicationYear","2014");
            wordNums2013[i]+=paperMapper.getPaperNum(map2013);
        }

        model.addAttribute("title",publisher);

        model.addAttribute("top0",top10Keywords[0]);
        model.addAttribute("num02019",wordNums2019[0]);
        model.addAttribute("num02016",wordNums2016[0]);
        model.addAttribute("num02013",wordNums2013[0]);

        model.addAttribute("top1",top10Keywords[1]);
        model.addAttribute("num12019",wordNums2019[1]);
        model.addAttribute("num12016",wordNums2016[1]);
        model.addAttribute("num12013",wordNums2013[1]);

        model.addAttribute("top2",top10Keywords[2]);
        model.addAttribute("num22019",wordNums2019[2]);
        model.addAttribute("num22016",wordNums2016[2]);
        model.addAttribute("num22013",wordNums2013[2]);

        model.addAttribute("top3",top10Keywords[3]);
        model.addAttribute("num32019",wordNums2019[3]);
        model.addAttribute("num32016",wordNums2016[3]);
        model.addAttribute("num32013",wordNums2013[3]);

        model.addAttribute("top4",top10Keywords[4]);
        model.addAttribute("num42019",wordNums2019[4]);
        model.addAttribute("num42016",wordNums2016[4]);
        model.addAttribute("num42013",wordNums2013[4]);

        model.addAttribute("top5",top10Keywords[5]);
        model.addAttribute("num52019",wordNums2019[5]);
        model.addAttribute("num52016",wordNums2016[5]);
        model.addAttribute("num52013",wordNums2013[5]);

        model.addAttribute("top6",top10Keywords[6]);
        model.addAttribute("num62019",wordNums2019[6]);
        model.addAttribute("num62016",wordNums2016[6]);
        model.addAttribute("num62013",wordNums2013[6]);

        model.addAttribute("top7",top10Keywords[7]);
        model.addAttribute("num72019",wordNums2019[7]);
        model.addAttribute("num72016",wordNums2016[7]);
        model.addAttribute("num72013",wordNums2013[7]);

        model.addAttribute("top8",top10Keywords[8]);
        model.addAttribute("num82019",wordNums2019[8]);
        model.addAttribute("num82016",wordNums2016[8]);
        model.addAttribute("num82013",wordNums2013[8]);

        model.addAttribute("top9",top10Keywords[9]);
        model.addAttribute("num92019",wordNums2019[9]);
        model.addAttribute("num92016",wordNums2016[9]);
        model.addAttribute("num92013",wordNums2013[9]);

        return "lineChart";
    }

}
