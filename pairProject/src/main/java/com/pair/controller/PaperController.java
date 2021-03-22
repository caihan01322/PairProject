package com.pair.controller;

import com.pair.dao.PaperMapper;
import com.pair.pojo.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PaperController {
    @Autowired
    PaperMapper paperMapper;
    @GetMapping("/paperList")
    public String getPaperList(){
        
    }
}
