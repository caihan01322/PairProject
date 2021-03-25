package com.example.demo.controller;

import com.example.demo.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pojo.Paper;

import java.util.List;

@RestController
public class PaperController {

    @Autowired
    PaperService paperService;

    @RequestMapping("/paper/query")
    public List<Paper> queryByKeyword(String keyword){
        return paperService.queryByKeyword(keyword);
    }

    @RequestMapping("/paper/delete")
    public void deleteByPaperID(int paperID){
        paperService.deletePaperByID(paperID);
    }
}
