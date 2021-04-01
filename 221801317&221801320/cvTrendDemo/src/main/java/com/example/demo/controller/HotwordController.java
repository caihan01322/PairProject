package com.example.demo.controller;


import com.example.demo.pojo.Keyword;
import com.example.demo.service.PaperKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotwordController {

    @Autowired
    PaperKeywordService paperKeywordService;

    @GetMapping("/hotword/query")
    public List<Keyword> hotwords(@RequestParam(name = "meeting",defaultValue = "")String meeting){
        List<Keyword> wordResentYear = paperKeywordService.getHotWordResentYear(meeting);
        for (Keyword word:wordResentYear) {
            word.setMeeting(meeting);
        }
        return wordResentYear;
    }

    @GetMapping("/hotword/trend")
    public List<Keyword> hotwordTreand(Keyword keyword){
        List<Keyword> wordResentYear = paperKeywordService.getWordTrend(keyword.getKeyword(),keyword.getMeeting());
        for (Keyword word:wordResentYear) {
            word.setMeeting(keyword.getMeeting());
        }
        return wordResentYear;
    }
}
