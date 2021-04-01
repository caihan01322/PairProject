package com.example.demo.controller;

import com.example.demo.pojo.EchartPojo;
import com.example.demo.pojo.Keyword;
import com.example.demo.service.PaperKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class ChartController {
    @Autowired
    PaperKeywordService paperKeywordService;

    @GetMapping("/hotword/frequency")
    public List<Keyword> getHotwordFrequency(@RequestParam(name = "meeting",defaultValue = "") String meeting){
        return paperKeywordService.getHotWordResentYear(meeting);
    }
    @GetMapping("/hotword/trends")
    public EchartPojo getHotwordTrends(@RequestParam(name = "meeting",defaultValue = "") String meeting){
        List<Keyword> hotWordResentYear = paperKeywordService.getHotWordResentYear(meeting);
        EchartPojo echartPojo = new EchartPojo();
        echartPojo.setKeywords(hotWordResentYear);
        List<Map<String,Long>> trends = new ArrayList<>();
        for (Keyword keyword: hotWordResentYear) {
            Map<String,Long> wordTrend = paperKeywordService.getWordTrendRecent(keyword.getKeyword(),keyword.getMeeting());
            trends.add(wordTrend);
        }
        echartPojo.setTrends(trends);
        return echartPojo;
    }
}
