package com.pair.controller;


import com.pair.dao.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class KeywordController {

    @Autowired
    KeywordMapper keywordMapper;

    @RequestMapping("/clouds")
    public String getKeyWords(Model model){
        List<String> keyWords = keywordMapper.getKeyWords();
        String kws[]=new String[10];
        for (int i=0;i<keyWords.size();i++){
            kws[i]=keyWords.get(i);
        }
        model.addAttribute("kw0",kws[0]);
        model.addAttribute("kw1",kws[1]);
        model.addAttribute("kw2",kws[2]);
        model.addAttribute("kw3",kws[3]);
        model.addAttribute("kw4",kws[4]);
        model.addAttribute("kw5",kws[5]);
        model.addAttribute("kw6",kws[6]);
        model.addAttribute("kw7",kws[7]);
        model.addAttribute("kw8",kws[8]);
        model.addAttribute("kw9",kws[9]);

        return "cloud";
    }

    @RequestMapping("/getPapers/{keyword}")
    public String getPapers(@PathVariable("keyword") String keyword){
        List<String> kid = keywordMapper.getKid(keyword);
        System.out.println(kid.toArray());

        return "cloud";
    }

}
