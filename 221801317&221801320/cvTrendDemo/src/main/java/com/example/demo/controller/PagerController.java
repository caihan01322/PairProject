package com.example.demo.controller;

import com.example.demo.pojo.Keyword;
import com.example.demo.pojo.Pager;
import com.example.demo.service.PaperKeywordService;
import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pojo.Paper;

import java.util.List;

@RestController
public class PagerController {

    @Autowired
    PaperService paperService;

    @Autowired
    PaperKeywordService paperKeywordService;

    @GetMapping("/paper/query")
    public Pager query(Paper param,@RequestParam(name = "page",defaultValue = "1") int page){
        int pageNumInLogic = page - 1;
        int paperCount = paperService.queryPaperNum(param);
        int totalPageNum = paperCount/Pager.PAGE_NUM + 1;

        List<Paper> papers = paperService.query(param, pageNumInLogic * Pager.PAGE_NUM, Pager.PAGE_NUM);

        return new Pager(papers,page,totalPageNum);
    }

    @GetMapping("/paper/query/hotwordRelated")
    public Pager hotwordRelatedPaper(Keyword keyword, @RequestParam(name = "page",defaultValue = "1") int page){
        int pageNumInLogic = page - 1;
        int paperCount = paperKeywordService.getPaperNumByKeyword(keyword);
        int totalPageNum = paperCount/Pager.PAGE_NUM + 1;

        List<Paper> papers = paperKeywordService.getPaperByKeywordLimit(keyword, pageNumInLogic * Pager.PAGE_NUM, Pager.PAGE_NUM);

        return new Pager(papers,page,totalPageNum);
    }


}
